package com.mayur.VideoPersistence.controller;

import com.mayur.VideoPersistence.service.VideoService;
import com.mayur.VideoPersistence.util.VideoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api/video")
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVideo(@RequestParam String videoCategory) {
        videoService.saveVideo(VideoCategory.valueOf(videoCategory.toUpperCase()));
    }
}
