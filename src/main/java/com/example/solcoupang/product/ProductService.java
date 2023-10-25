package com.example.solcoupang.product;

import com.example.solcoupang.common.BaseException;
import com.example.solcoupang.common.BaseResponseStatus;
import com.example.solcoupang.product.model.Delete.DeleteRes;
import com.example.solcoupang.product.model.Read.GetProductDetailRes;
import com.example.solcoupang.product.model.create.PostProductReq;
import com.example.solcoupang.product.model.create.PostProductRes;
import com.example.solcoupang.product.model.patch.PatchProductReq;
import com.example.solcoupang.product.model.patch.PatchProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;


    public GetProductDetailRes getPoductDetail(Long productId) throws BaseException {
        try {
            return productDao.getProductDetail(productId);
        } catch (Exception var2) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public PostProductRes postProductDetail(PostProductReq postProductReq) {
        try{
            return productDao.postProductDetail(postProductReq);
        } catch (Exception var2) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public PatchProductRes patchProductDetail(PatchProductReq patchProductReq) {
        try{
            return productDao.patchProductDetail(patchProductReq);
        } catch (Exception var2) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public DeleteRes deleteProduct(Long productId) {
        DeleteRes deleteRes = new DeleteRes(productId);
        try{
            int resultImg = this.productDao.checkProductImgs(productId);
            if (resultImg == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_IMAGE);
            }
            else{
                Integer resImg = productDao.deleteProductImgs(productId);
                deleteRes.setRemovedImages(resImg);
            }

            int resultOption = this.productDao.checkProductOptions(productId);
            if (resultOption == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_OPTION);
            }
            else{
                Integer resOp = productDao.deleteProductOptions(productId);
                deleteRes.setRemovedOptions(resOp);
            }

            int resultProduct = this.productDao.checkProduct(productId);
            if (resultProduct == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_PRODUCT);
            }
            else productDao.deleteProduct(productId);
            return deleteRes;
        }catch (Exception var2) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }

    }
}
