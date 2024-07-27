package com.assessment.delivery.config;

import com.assessment.delivery.dto.PaymentStatusDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * @author Krishna Chaitanya
 */
@Configuration
public class AppConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PaymentStatusDetails> kafkaListenerContainerFactory(
            ConsumerFactory<String, PaymentStatusDetails> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, PaymentStatusDetails> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.getContainerProperties().setObservationEnabled(true);
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }

    /**
     * This bean has been added so that in the swagger-ui, we will see snake_case attributes instead of camelCase <br/>
     * Swagger UI Path :- /api/swagger-ui/index.html <br/>
     * <a href="https://github.com/springdoc/springdoc-openapi/issues/66#issuecomment-560335774">Reference link</a>
     */
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

}
