package com.mayur.VideoAnalyzer.configuration;

import com.mayur.VideoAnalyzer.request.VideoAnalysisRequest;
import com.mayur.VideoAnalyzer.util.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${com.mayur.VideoPersistence.kafka.bootstrap-servers}")
    private String bootstrapServers;


    /*
    * The Group ID determines which consumers belong to which group. You can assign Group IDs via configuration when you
    * create the consumer client. If there are four consumers with the same Group ID assigned to the same topic,
    * they will all share the work of reading from the same topic.
    * If there are eight partitions, each of those four consumers will be assigned two partitions. What if there are
    * nine partitions? That means the leftover partition will be assigned to the first consumer in the group so that
    * one consumer reads from three partitions and the rest of the consumers read from two partitions. It’s the broker’s
    * job to continually ensure that partitions are evenly distributed among the connected consumers.
    * */

    public ConsumerFactory<String, VideoAnalysisRequest> videoAnalysisRequestConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.VIDEO_ANALYSIS_REQUEST_GROUP_ID);
        //to enable exactly-once semantics in Kafka of more check :- https://www.baeldung.com/kafka-message-delivery-semantics
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        JsonDeserializer<VideoAnalysisRequest> deserializer = new JsonDeserializer<VideoAnalysisRequest>();
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VideoAnalysisRequest> videoAnalysisRequestConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, VideoAnalysisRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(videoAnalysisRequestConsumerFactory());
        return factory;
    }
}
