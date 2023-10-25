package com.example.solcoupang.product.model.Read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class GetproductRes {
    private Long productId;
    private Long sellerId;
    private Date registrationDate;
    private String productName;
    private String countryOfManufacture;
    private String weight;
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
}
