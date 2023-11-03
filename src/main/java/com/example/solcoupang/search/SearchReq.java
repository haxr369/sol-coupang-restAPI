package com.example.solcoupang.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SearchReq {
    private String region;
    private String sort;
    private String sel_date;
    private String sel_date2;
    private Long min_price;
    private Long max_price;
    private List<Long> keywords;
}
