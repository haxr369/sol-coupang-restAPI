package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.Product;
import com.example.solcoupang.product.domain.QProduct;
import com.example.solcoupang.product.domain.QProductContent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


interface ProductRepositoryCustom {
    List<Product> findByContentNumb(Long numb);
}

//@RequiredArgsConstructor
//class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
//    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<Product> findByContentNumb(Long numb) {
//
////        long count = jpaQueryFactory.select(QProductContent.productContent)
////                .from(QProductContent.productContent)
//
//        return jpaQueryFactory.select(QProduct.product)
//                .from(QProduct.product)
//                .where()
//    }
//}


public interface ProductRepository extends JpaRepository<Product, Long> {

    // 일반적 join 사용
    @Query("SELECT p FROM Product p LEFT JOIN Seller s ON p.seller.sellerId = s.sellerId WHERE s.sellerName = :name ")
    Product findBySellerName(@Param("name") String name);

    // Fetch join 사용
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.seller ps " +
            "LEFT JOIN FETCH p.productContents " +
            "WHERE p.productId = :id")
    Product findByProductIdWithFetch(@Param("id") Long id);

    @Query("SELECT distinct p FROM Product p " +
            "LEFT JOIN FETCH p.seller ps " +
            "LEFT JOIN FETCH p.productContents ")
    List<Product> findAllProduct();

    Product findByProductId(Long id);

    // 1+N 문제 발생
    @Query("SELECT  p FROM Product p WHERE p.productId = :id ")
    Product findByProductIdWOFetch(@Param("id") Long id);

}
