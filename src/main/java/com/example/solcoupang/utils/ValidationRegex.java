package com.example.solcoupang.utils;

import com.example.solcoupang.product.model.Read.GetproductRes;
import com.example.solcoupang.product.model.Read.ProductImage;
import com.example.solcoupang.product.model.Read.ProductOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
    public ValidationRegex() {
    }

    public static boolean isRegexEmail(String target) {
        // 이메일 형식 체크
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    @Component
    @Slf4j
    public static class RowMappers {
        public RowMapper<GetproductRes> productRowMapper() {
            return ((rs, rowNum) -> {
                // rs에서 하나씩 값을 추출해서 List<GetproductRes> 형태로
                return GetproductRes.builder()
                        .productId(rs.getLong("productId"))
                        .sellerId(rs.getLong("sellerId"))
                        .registrationDate(rs.getDate("registrationDate"))
                        .productName(rs.getString("productName"))
                        .countryOfManufacture(rs.getString("countryOfManufacture"))
                        .weight(rs.getString("weight"))
                        .kcCertificationInformation(rs.getString("kcCertificationInformation"))
                        .manufacturer(rs.getString("manufacturer"))
                        .importer(rs.getString("importer"))
                        .build();
            });
        }

        public RowMapper<Integer> itemRowMapper() {
            return ((rs, rowNum) -> {
    //            log.info(String.valueOf(rs.getInt(1)));
                return rs.getInt(1);
            });
        }

        public RowMapper<ProductOption> productOptionsRowMapper(){
            return ((rs, rowNum) -> ProductOption.builder()
                    .optionId(rs.getLong("optionId"))
                    .price(rs.getLong("price"))
                    .optionTitle(rs.getString("optionTitle"))
                    .build());
        }

        public RowMapper<ProductImage> productImageRowMapper(){
            return ((rs, rowNum) -> ProductImage.builder()
                    .contentId(rs.getLong("contentId"))
                    .contentImgUrl(rs.getString("contentImgUrl")).build());
        }
    }
}
