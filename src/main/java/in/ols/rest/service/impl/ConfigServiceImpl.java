package in.ols.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ols.rest.model.Config;
import in.ols.rest.repository.ConfigRepository;
import in.ols.rest.service.IConfigService;


@Service
public class ConfigServiceImpl implements IConfigService {
   
   @Autowired
   ConfigRepository configRepository;

   
   @Override
   public <T> T getConfigValue(String property, Class<T> valueType){
      Config config = configRepository.findByProperty(property);
      if(config == null){
         return null;
      }
      
      try {
         return valueType.cast(config.getValue());
      } catch (ClassCastException ex) {
      }
      
      return null;
   }

   @Override
   public <T> T getConfigValue(String property, Class<T> valueType, T defaultValue) {
      Config config = configRepository.findByProperty(property);
      if(config == null){
         return defaultValue;
      }
      
      try {
         return valueType.cast(config.getValue());
      } catch (ClassCastException ex) {
      }
      
      return null;
   }

   
}
