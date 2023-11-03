package com.example.solcoupang.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class Controller {

    @GetMapping(value = "/search/{type}")
    public String getReq(@PathVariable("type") String type, SearchReq searchReq) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date selDate = format.parse(searchReq.getSel_date());
        Date selDate2 = format.parse(searchReq.getSel_date2());

        log.info("sel_date : "+selDate+" sel_date2 : "+selDate2);

        // java.sql.Date로 변환
        java.sql.Date sqlSelDate = new java.sql.Date(selDate.getTime());
        java.sql.Date sqlSelDate2 = new java.sql.Date(selDate2.getTime());

        log.info("sel_date : "+sqlSelDate+" sel_date2 : "+sqlSelDate2);
        return "type : "+type+" "+searchReq.toString();
    }

    @GetMapping(value = "/search/detail")
    public DetailAccommo getDetail(){

        List<String> listA = new ArrayList<>();
        listA.add("dsfsfsddf");
        listA.add("ewffweffe");
        listA.add("grbnrrnnn");
        listA.add("5y464h649");
        listA.add("dsfsfsddf");
        listA.add("grth4ns44");


        DetailAccommo detailAccommo = DetailAccommo.builder()
                .accommodationId("32324")
                .type("HOTEL")
                .accommodationName("2244")
                .subImgUrls(listA).build();
        return detailAccommo;
    }
}
