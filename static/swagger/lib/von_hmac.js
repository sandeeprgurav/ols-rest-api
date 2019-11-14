//var key = 'JB';
//var base64Secret = 'JB';
var schema = "JB";

function getDate() {
    
    var months = { 
    'Jan' : '01', 'Feb' : '02', 'Mar' : '03', 'Apr' : '04', 'May' : '05','Jun' : '06', 
    'Jul' : '07', 'Aug' : '08', 'Sep' : '09', 'Oct' : '10', 'Nov' : '11','Dec' : '12'
    }

    var todayString = new Date().toGMTString();
    var cYear = todayString.substring(12,16);
    var cMonth = eval('months.'+todayString.substring(8,11));
    var cDate = todayString.substring(5,7);
    var cHour = todayString.substring(17,19);
    var cMin = todayString.substring(20,22);
    var cSec = todayString.substring(23,25);
    
    return cYear + "-" + cMonth + "-" + cDate + " " + cHour + ":" + cMin + ":" + cSec + " " + "GMT";
   
}

function getEncodedUri(fullUri){
    var uriParts = fullUri.split("//")[1].split("/");
    return "/" + uriParts.splice(1, uriParts.length).join("/");
}



function getAuthHeader(request, key, secret){
	var method = request.method;
	var fullUri = request.url;
	var vonDate = getDate();
	var encodedUri = getEncodedUri(fullUri.split("?")[0]);

	// Build the request body string from the Postman request.data object
	var requestBody = request.body;

	var b64BodyContent = "";
	if(method == 'GET'){
	    b64BodyContent = CryptoJS.MD5(b64BodyContent).toString();
	}
	else {
	    // MD5 hash and convert the request body string to base 64
		  if(requestBody == null) {
				requestBody = "";
			}

	    b64BodyContent = CryptoJS.MD5(requestBody).toString();
	}
	var stringToSign = (method + "\n" + encodedUri + "\n" + vonDate + "\n" + b64BodyContent + "\n").toLowerCase();
	var signature = CryptoJS.HmacSHA1(stringToSign, secret).toString(CryptoJS.enc.Base64);
	var authHeader = schema + " " + key + ":" + signature;
	return authHeader;
}