package com.B2Techyoutubechannel.SpringbootMongoDBPractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfigration {
    
    /**
     * configure validation
     * before persist the null value in the DB , not allow the null value & display validation to users
     * @return
     */
@Bean
public ValidatingMongoEventListener validationMongoEventListener(){
    return new ValidatingMongoEventListener(validator());
}

@Bean
public LocalValidatorFactoryBean validator(){
    return new LocalValidatorFactoryBean();
}


}
