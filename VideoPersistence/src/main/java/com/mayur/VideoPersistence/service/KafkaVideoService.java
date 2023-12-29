package com.mayur.VideoPersistence.service;

import com.mayur.VideoAnalyzer.request.VideoAnalysisRequest;
import com.mayur.VideoPersistence.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaVideoService {

    @Autowired
    private KafkaTemplate<String, VideoAnalysisRequest> videoKafkaTemplate;

    void sendVideoAnalysisRequestWithCallback(VideoAnalysisRequest videoAnalysisRequest) {
        ListenableFuture<SendResult<String, VideoAnalysisRequest>> future = videoKafkaTemplate.send(Constants.VIDEO_ANALYSIS_REQUEST_TOPIC_NAME,videoAnalysisRequest );
        future.addCallback(new ListenableFutureCallback<SendResult<String, VideoAnalysisRequest>>() {
            @Override
            public void onSuccess(SendResult<String, VideoAnalysisRequest> result) {
                log.info("Message [{}] delivered with offset {}",
                        videoAnalysisRequest.toString(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to deliver message [{}]. {}",
                        videoAnalysisRequest.toString(),
                        ex.getMessage());
            }
        });
    }
}
