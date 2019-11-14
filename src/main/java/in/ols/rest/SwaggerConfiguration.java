package in.ols.rest;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfiguration {

   @Autowired
   private Environment env;

   private SpringSwaggerConfig springSwaggerConfig;

   @Autowired
   public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
      this.springSwaggerConfig = springSwaggerConfig;
   }

   @Bean
   public SwaggerSpringMvcPlugin customImplementation() {
      return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
            .apiInfo(new ApiInfo(
                  "TransLinkers REST API v0.1",
                  null,
                  null,
                  null,
                  null,
                  null))
            .useDefaultResponseMessages(false)
            .includePatterns(".*/api/.*");
   }
      
}
