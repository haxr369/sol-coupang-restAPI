package com.example.solcoupang.product.dto.product;

import com.example.solcoupang.common.annotation.PhoneNumber;
import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.ProductContent;
import com.example.solcoupang.product.domain.Seller;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class ProductRequestDto {
    private Long sellerId;
    private LocalDate registrationDate;
    @NotBlank // null, "", " " 모두 허용 X
    private String productName;
    private String countryOfManufacture;
    @Min(value = 10) // 10 이상
    private Long weight;
    //@Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3,4})[-]?(\\d{4})$") //010-6604-3466 형태를 맞추도록
    @PhoneNumber
    private String phoneNumber;
    @Size(min = 7) // 7글자 이상
    private String kcCertificationInformation;
    private String manufacturer;
    private String importer;
    private List<String> productContents;

    // DTO로 api 요청을 받고 이를 entity로 어떻게 바꾸지?

    public Product toEntity(Seller seller){
        // 1대 N을 위해 굳이 product를 만들다만다.
        //        // ProductContent의 외래키를 위해 만들다만 product를 리스트로 만들어서 넣어준다.
//        List<ProductContent> productContentList = productContents.stream()
//                .map(content ->
//                        ProductContent
//                                .builder()
//                                .product(product)
//                                .contentImgUrl(content)
//                                .build())
//                .collect(Collectors.toList());
//
//        // 1대N 관계에서 엔티티를 만들기 위해 결국 set 함수를 사용하게 된다.
//        product.setProductContents(productContentList);
        return Product.builder()
                .seller(seller)
                .registrationDate(registrationDate)
                .productName(productName)
                .countryOfManufacture(countryOfManufacture)
                .weight(weight)
                .kcCertificationInformation(kcCertificationInformation)
                .manufacturer(manufacturer)
                .importer(importer)
                .productContents(new ArrayList<>())
                .build();
    }


}
