package com.example.solcoupang.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DetailAccommo {
    private String accommodationId;
    private String accommodationName;
    private String type;
    private List<String> subImgUrls;
}
