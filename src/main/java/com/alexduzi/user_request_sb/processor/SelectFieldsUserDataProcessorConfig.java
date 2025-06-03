package com.alexduzi.user_request_sb.processor;

import com.alexduzi.user_request_sb.dto.UserDTO;
import com.alexduzi.user_request_sb.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFieldsUserDataProcessorConfig {

    private static Logger logger = LoggerFactory.getLogger(SelectFieldsUserDataProcessorConfig.class);
    private int counter = 1;

    @Bean
    public ItemProcessor<UserDTO, User> selectFieldsUserDataProcessor() {
        return item -> {
            User user = new User();
            user.setLogin(item.getLogin());
            user.setName(item.getName());
            user.setAvatarUrl(item.getAvatarUrl());
            logger.info("[PROCESSOR STEP] select fields user " + counter + " - " + user);
            counter++;
            return user;
        };
    }
}
