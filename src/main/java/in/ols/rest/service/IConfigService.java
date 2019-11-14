package in.ols.rest.service;

import org.springframework.cache.annotation.Cacheable;

public interface IConfigService {

   @Cacheable(cacheNames="configCache", key="#property", unless = "#result == null")
   public <T> T getConfigValue(String property, Class<T> valueType);
   
   public <T> T getConfigValue(String property, Class<T> valueType, T defaultValue);
   
}
