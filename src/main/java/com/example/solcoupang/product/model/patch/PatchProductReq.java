package com.example.solcoupang.product.model.patch;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class PatchProductReq {
    private Long productId;
    private Long sellerId;
    private String productName;
    private Date registrationDate;
    private String countryOfManufacture;
    private String weight;
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
}
