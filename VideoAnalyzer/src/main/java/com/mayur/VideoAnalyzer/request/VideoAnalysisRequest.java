package com.mayur.VideoAnalyzer.request;

import com.mayur.VideoPersistence.util.VideoCategory;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VideoAnalysisRequest implements Serializable {
    private String Id;
    private VideoCategory videoCategory;
}
