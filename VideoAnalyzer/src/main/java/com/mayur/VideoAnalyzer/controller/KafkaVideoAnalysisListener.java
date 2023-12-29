package com.mayur.VideoAnalyzer.controller;

import com.mayur.VideoAnalyzer.request.VideoAnalysisRequest;
import com.mayur.VideoAnalyzer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaVideoAnalysisListener {

    @KafkaListener(topics = Constants.VIDEO_ANALYSIS_REQUEST_TOPIC_NAME,
            groupId = Constants.VIDEO_ANALYSIS_REQUEST_GROUP_ID,
            containerFactory = "videoAnalysisRequestConcurrentKafkaListenerContainerFactory"
    )
    void analyzeVideo(@Payload VideoAnalysisRequest videoAnalysisRequest) {
        log.info(videoAnalysisRequest.toString());
    }
}
