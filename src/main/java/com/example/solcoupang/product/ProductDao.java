package com.example.solcoupang.product;


import java.util.List;

import com.example.solcoupang.common.BaseException;
import com.example.solcoupang.common.BaseResponseStatus;
import com.example.solcoupang.product.model.Read.GetProductDetailRes;
import com.example.solcoupang.product.model.Read.GetproductRes;
import com.example.solcoupang.product.model.Read.ProductImage;
import com.example.solcoupang.product.model.Read.ProductOption;
import com.example.solcoupang.product.model.create.PostProductOption;
import com.example.solcoupang.product.model.create.PostProductReq;
import com.example.solcoupang.product.model.create.PostProductRes;
import com.example.solcoupang.product.model.create.ProductSimpleRes;
import com.example.solcoupang.product.model.patch.PatchProductReq;
import com.example.solcoupang.product.model.patch.PatchProductRes;
import com.example.solcoupang.user.model.PatchUserReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor // jdbcTemplate 의존성 추가
@Slf4j
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMappers rowMappers;

    public GetProductDetailRes getProductDetail(Long productId) {
        String getUsersQuery = "SELECT * FROM product WHERE productId =?";
        String getProductOptionQuery = "SELECT * FROM productoption WHERE productId =?";
        String getProductImageQuery = "SELECT * FROM productcontent WHERE productId =?";
        try{
            GetproductRes getproductRes = jdbcTemplate.query(getUsersQuery, rowMappers.productRowMapper(),productId).get(0);
            List<ProductOption> productOptionList = jdbcTemplate.query(getProductOptionQuery, rowMappers.productOptionsRowMapper(),productId);
            List<ProductImage> productImageList = jdbcTemplate.query(getProductImageQuery, rowMappers.productImageRowMapper(),productId);
            return GetProductDetailRes.builder()
                    .getproductRes(getproductRes)
                    .productImages(productImageList)
                    .productOptions(productOptionList).build();
        }
        catch(RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }

    public PostProductRes postProductDetail(PostProductReq postProductReq) {
        String createProductQuery = "INSERT INTO Product (sellerId, registrationDate, productName, countryOfManufacture, weight, kcCertificationInformation, manufacturer, importer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String createOptionQuert = "INSERT INTO productOption (productId, price, optionTitle) VALUES (?,?,?)";
        String createImageQuery = "INSERT INTO ProductContent (productId, contentImgUrl) VALUES (?,?)";

        ProductSimpleRes productSimpleRes = postProductReq.getProductSimpleRes();
//        log.info(productSimpleRes.getProductName());
        List<PostProductOption> postProductOptionList = postProductReq.getPostProductOptionList();
//        log.info("옵션 이름 : "+postProductOptionList.get(0).getOptionTitle());
        List<String> productImgList = postProductReq.getProductImgUrls();
//        log.info("url : "+productImgList.get(0));

        try{
            Object[] createProductParams = new Object[]{
                    productSimpleRes.getSellerId(), // Long 타입
                    productSimpleRes.getRegistrationDate(),
                    productSimpleRes.getProductName(),
                    productSimpleRes.getCountryOfManufacture(),
                    productSimpleRes.getWeight(),
                    productSimpleRes.getKcCertificationInformation(),
                    productSimpleRes.getManufacturer(),
                    productSimpleRes.getImporter()
            };
            jdbcTemplate.update(createProductQuery, createProductParams);
            String lastInsertIdQuery = "select last_insert_id()";
            Long productId = jdbcTemplate.queryForObject(lastInsertIdQuery, Long.TYPE);
            log.info("추가된 id : "+productId);

            for(PostProductOption obj : postProductOptionList){
                Object[] createProductOptionParams = new Object[]{
                        productId,
                        obj.getPrice(),
                        obj.getOptionTitle()
                };
                jdbcTemplate.update(createOptionQuert, createProductOptionParams);
            }

            for(String url : productImgList){
                Object[] createProductImgParams = new Object[]{
                        productId,
                        url
                };
                jdbcTemplate.update(createImageQuery, createProductImgParams);
            }
            return PostProductRes.builder().productId(productId).productName(productSimpleRes.getProductName()).build();
        } catch(RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }

    }

    public PatchProductRes patchProductDetail(PatchProductReq patchProductReq) {
        String getProductQuery = "SELECT * FROM Product WHERE productId = ?";
        String updateProductQuery = "UPDATE Product SET productName = ?, registrationDate = ?, countryOfManufacture = ?, weight = ?, kcCertificationInformation = ?, manufacturer = ?, importer = ? WHERE productId = ?";

        try {
            // 제품 정보 조회
            List<GetproductRes> productResults = jdbcTemplate.query(getProductQuery, rowMappers.productRowMapper(), patchProductReq.getProductId());
            if (productResults.isEmpty()) {
                // 조회된 결과가 없을 경우 예외 처리 또는 다른 작업 수행
                throw new BaseException(BaseResponseStatus.DATA_NOT_FOUND);
            }

            GetproductRes getproductRes = productResults.get(0);

            // 입력된 값과 기존 값 비교 및 업데이트
            if (patchProductReq.getProductName() != null && !getproductRes.getProductName().equals(patchProductReq.getProductName())) {
                getproductRes.setProductName(patchProductReq.getProductName());
            }
            if (patchProductReq.getRegistrationDate() != null && !getproductRes.getRegistrationDate().equals(patchProductReq.getRegistrationDate())) {
                getproductRes.setRegistrationDate(patchProductReq.getRegistrationDate());
            }
            if (patchProductReq.getCountryOfManufacture() != null && !getproductRes.getCountryOfManufacture().equals(patchProductReq.getCountryOfManufacture())) {
                getproductRes.setCountryOfManufacture(patchProductReq.getCountryOfManufacture());
            }
            if (patchProductReq.getWeight() != null && !getproductRes.getWeight().equals(patchProductReq.getWeight())) {
                getproductRes.setWeight(patchProductReq.getWeight());
            }
            if (patchProductReq.getKcCertificationInformation() != null &&!getproductRes.getKcCertificationInformation().equals(patchProductReq.getKcCertificationInformation())) {
                getproductRes.setKcCertificationInformation(patchProductReq.getKcCertificationInformation());
            }
            if (patchProductReq.getManufacturer() != null && !getproductRes.getManufacturer().equals(patchProductReq.getManufacturer())) {
                getproductRes.setManufacturer(patchProductReq.getManufacturer());
            }
            if (patchProductReq.getImporter() != null && !getproductRes.getImporter().equals(patchProductReq.getImporter())) {
                getproductRes.setImporter(patchProductReq.getImporter());
            }

            // 업데이트 쿼리 실행
            Object[] updateProductParams = new Object[]{
                    getproductRes.getProductName(),
                    getproductRes.getRegistrationDate(),
                    getproductRes.getCountryOfManufacture(),
                    getproductRes.getWeight(),
                    getproductRes.getKcCertificationInformation(),
                    getproductRes.getManufacturer(),
                    getproductRes.getImporter(),
                    patchProductReq.getProductId()
            };

            int rowsAffected = jdbcTemplate.update(updateProductQuery, updateProductParams);

            if (rowsAffected == 1) {
                // 업데이트가 성공하면 원하는 결과를 반환
                return PatchProductRes.builder()
                        .productId(getproductRes.getProductId())
                        .productName(getproductRes.getProductName()).build();
            } else {
                // 업데이트가 실패한 경우 예외 처리 또는 다른 작업 수행
                throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
            }
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }


    public int deleteUser(int userId) {
        String deleteUserNameQuery = "update USER set status = ? where id = ? ";
        Object[] deleteUserNameParams = new Object[]{"INACTIVE", userId};
        return this.jdbcTemplate.update(deleteUserNameQuery, deleteUserNameParams);
    }


    public Integer checkProduct(Long productId) {
        String checkProductQuery = "select exists(select * from Product where productId = ?)";
        try{
            return jdbcTemplate.queryForObject(checkProductQuery, rowMappers.itemRowMapper(), productId);
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }

    }
    public Integer checkProductImgs(Long productId) {
        String checkImgQuery = "select exists(select * from ProductContent where productId = ?)";
        try{
            return jdbcTemplate.queryForObject(checkImgQuery, rowMappers.itemRowMapper(), productId);
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }

    }
    public Integer checkProductOptions(Long productId) {
        String checkOptionQuery = "select exists(select * from ProductOption where productId = ?)";
        try{
            return jdbcTemplate.queryForObject(checkOptionQuery, rowMappers.itemRowMapper(), productId);
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }

    }

    public Integer deleteProductImgs(Long productId) {
        try {
            String deleteImgQuery = "DELETE FROM ProductContent WHERE productId = ?";
            int rowsAffected = jdbcTemplate.update(deleteImgQuery, productId);
            log.info(rowsAffected + "개 이미지 제거!");
            return rowsAffected;
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }


    public Integer deleteProductOptions(Long productId) {
        try {
            String deleteOptionsQuery = "DELETE FROM ProductOption WHERE productId = ?";
            int rowsAffected = jdbcTemplate.update(deleteOptionsQuery, productId);
            log.info(rowsAffected + "개 옵션 제거!");
            return rowsAffected;
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }

    public void deleteProduct(Long productId) {
        try {
            String deleteProductQuery = "DELETE FROM Product WHERE productId = ?";
            int rowsAffected = jdbcTemplate.update(deleteProductQuery, productId);
            log.info(rowsAffected + "개 상품 제거!");
        } catch (RuntimeException e) {
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }




}

