package com.mayur.VideoPersistence.configuration;

import com.mayur.VideoPersistence.util.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Collections;

@Configuration
public class KafkaTopicConfig {

    /*
    * We have kept retention period of 7 days
    * */

    @Bean
    NewTopic videoAnalysisTopicCreation() {
        return TopicBuilder.name(Constants.VIDEO_ANALYSIS_REQUEST_TOPIC_NAME).configs(Collections.singletonMap("retention.ms", "604800000")).build();
    }
}
