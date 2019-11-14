package in.ols.rest.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {

	/**
	 * Encrypts plainText in AES using the secret/master key
	 * @param plainText
	 * @param secKey
	 * @return
	 * @throws Exception 
	 */
	public static String encryptText(String plainText, String secretKey) throws Exception{
		// MasterKey (basically key used to encrypt the data) needs to decided by Legal team
		byte[] password = new String(secretKey).getBytes();
		Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, keySpec);
		String encryptedText = Base64.encodeBase64String(aesCipher.doFinal(plainText.getBytes()));
		return encryptedText;
	}

	/**
	 * Decrypts encrypted String using the key used for encryption.
	 * @param encrpytText
	 * @param secKey
	 * @return
	 * @throws Exception
	 */
	public static String decryptText(String encrpytText, String key) throws Exception {
		byte[] password = new String(key).getBytes();
		Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(password, "AES");
		aesCipher.init(Cipher.DECRYPT_MODE, keySpec);
		String decryptedText = new String(aesCipher.doFinal(Base64.decodeBase64(encrpytText)));
		return decryptedText;
	}
}
