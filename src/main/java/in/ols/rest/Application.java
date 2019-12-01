package in.ols.rest;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.google.common.cache.CacheBuilder;

import in.ols.rest.filters.UserAuditor;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableCaching
@EnableMongoAuditing(auditorAwareRef="userAuditorAware")
@EnableMongoRepositories(basePackages = { "in.ols.rest.repository" })
public class Application{
	
	final static String CUSTOM_PROPERTY_FILE = "/var/resources/olsProperties.properties";
	
	@Bean(name = "userAuditorAware")
    public UserAuditor userAuditorAware(){
        return new UserAuditor();
    }
   
   public static void main(String[] args) {
	   new SpringApplicationBuilder(Application.class)
				.properties("customProperties", "spring.config.location:file:" + CUSTOM_PROPERTY_FILE)
				.build().run(args);	
  }
   
   @Bean
   public CacheManager cacheManager() {
      
      GuavaCacheManager staticValuesCache = new GuavaCacheManager("staticValues");
      staticValuesCache.setAllowNullValues(false);
      staticValuesCache.setCacheBuilder(CacheBuilder.newBuilder().maximumSize(2500).expireAfterWrite(2L, TimeUnit.DAYS));
      
      GuavaCacheManager configCache = new GuavaCacheManager("configCache");
      configCache.setAllowNullValues(false);
      configCache.setCacheBuilder(CacheBuilder.newBuilder().maximumSize(2500).expireAfterWrite(1L, TimeUnit.DAYS));
      
      GuavaCacheManager customerCache = new GuavaCacheManager("customerCache");
      customerCache.setAllowNullValues(false);
      customerCache.setCacheBuilder(CacheBuilder.newBuilder().maximumSize(2500).expireAfterWrite(2L, TimeUnit.DAYS));
      
      CompositeCacheManager cacheManager = new CompositeCacheManager(staticValuesCache,customerCache,configCache);
      cacheManager.setFallbackToNoOpCache(true);

      return cacheManager;
   }
}
