package in.ols.rest.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;


@Configuration
@EnableMongoRepositories("in.translinkers.rest.model")
@EnableMongoAuditing
public class MongoConfiguration extends AbstractMongoConfiguration {

	 	@Value("${spring.data.mongodb.uri}")
	    private String mongoUri;

	    @Value("${spring.data.mongodb.database}")
	    private String mongoDB;
		
		@Override
	    protected String getDatabaseName() {
	      return this.mongoDB;
	    }

	   @Override
	   public Mongo mongo() throws Exception {
//	      return new MongoClient(parseServers("192.168.2.4:27017"));
//	      return new MongoClient(new MongoClientURI( "mongodb://192.168.2.4:27017/translinkers_local" ));
	      return new MongoClient(new MongoClientURI(this.mongoUri));
	   }

   @Bean
   public GridFsTemplate gridFsTemplate() throws Exception {
      return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
   }
      
   @SuppressWarnings("unused")
   private List<ServerAddress> parseServers(String serverNames) throws UnknownHostException {
      String[] parts = serverNames.split(",");
      List<ServerAddress> servers = new ArrayList<>();
      for (int i = 0; i < parts.length; i++) {
         String part = parts[i];
         try {
            int colonPos = part.indexOf(":");
            if (colonPos > 0 && (colonPos < part.length() - 1)) {
               String name = part.substring(0, colonPos);
               String portStr = part.substring(colonPos + 1);
               servers.add(new ServerAddress(name, Integer.valueOf(portStr)));
            }
         } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal port number in: " + part);
         } catch (Exception e) {
            throw new UnknownHostException("Unknown Host");
         }
      }
      if (servers.isEmpty()) {
         throw new IllegalArgumentException("No (valid) servers defined!");
      }
      return servers;
   }
   
   private List<Converter<?,?>> converters = new ArrayList<Converter<?,?>>();
      
}
