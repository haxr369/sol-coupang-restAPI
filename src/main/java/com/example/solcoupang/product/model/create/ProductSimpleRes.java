package com.example.solcoupang.product.model.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProductSimpleRes {
    private Long sellerId;
    private String productName;
    private Date registrationDate;
    private String countryOfManufacture;
    private String weight;
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
}
