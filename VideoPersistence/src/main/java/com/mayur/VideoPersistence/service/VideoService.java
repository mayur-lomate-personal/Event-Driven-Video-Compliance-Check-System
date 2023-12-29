package com.mayur.VideoPersistence.service;

import com.mayur.VideoAnalyzer.request.VideoAnalysisRequest;
import com.mayur.VideoPersistence.util.Constants;
import com.mayur.VideoPersistence.util.VideoCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Slf4j
@Service
public class VideoService {

    @Autowired
    private KafkaVideoService kafkaVideoService;

    public void saveVideo(VideoCategory videoCategory) {
        String videoID = UUID.randomUUID().toString();
        kafkaVideoService.sendVideoAnalysisRequestWithCallback(new VideoAnalysisRequest(videoID, videoCategory));
    }
}
