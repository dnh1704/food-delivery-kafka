package com.assessment.payment.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private final KafkaProperties kafkaProperties;

    @Autowired
    public KafkaConsumerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> NhanSukafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ConsumerFactory());
        factory.setBatchListener(true);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfigs());
    }

    @Bean
    public Map<String, Object> ConsumerConfigs() {
        Map<String, Object> props2 = kafkaProperties.buildConsumerProperties();
        props2.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props2.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props2.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.117.217:29092" );
//        props2.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getProperties().get("schema-registry-url"));
//        props2.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
//        props2.put(KafkaAvroDeserializerConfig.USE_LATEST_VERSION, false);
//        props2.put(KafkaAvroDeserializerConfig.AUTO_REGISTER_SCHEMAS, false);
//        props2.put(SpecificAvroWithSchemaDeserializer.AVRO_VALUE_RECORD_TYPE, NhanSuMessage.class);
        return props2;
    }
}