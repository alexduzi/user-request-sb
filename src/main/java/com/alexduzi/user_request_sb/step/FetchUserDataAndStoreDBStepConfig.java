package com.alexduzi.user_request_sb.step;

import com.alexduzi.user_request_sb.dto.UserDTO;
import com.alexduzi.user_request_sb.entities.User;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FetchUserDataAndStoreDBStepConfig {

    private final PlatformTransactionManager transactionManager;

    @Value("${chunkSize}")
    private int chunkSize;

    public FetchUserDataAndStoreDBStepConfig(@Qualifier("transactionManagerApp") PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step fetchUserDataAndStoreDBStep(ItemReader<UserDTO> fetchUserDataReader,
                                            ItemProcessor<UserDTO, User> selectFieldsUserDataProcessor,
                                            ItemWriter<User> insertUserDataDBWriter,
                                            JobRepository jobRepository) {
        return new StepBuilder("fetchUserDataAndStoreDBStep", jobRepository)
                .<UserDTO, User>chunk(chunkSize, transactionManager)
                .reader(fetchUserDataReader)
                .processor(selectFieldsUserDataProcessor)
                .writer(insertUserDataDBWriter)
                .build();
    }
}
