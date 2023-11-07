package com.example.solcoupang.product.repository;

import com.example.solcoupang.product.domain.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import static com.example.solcoupang.product.domain.QProduct.*;
import static com.example.solcoupang.product.domain.QProductContent.*;
import java.util.List;


interface ProductRepositoryCustom {
    List<Product> findByContentNumb(Long numb);
    List<Product> findByProductIdFetchImpl(Long id);
    List<Product> findByProductIdWoFetchImpl(Long id);

    List<Product> findByProductName(String name);

    List<Product> findBetweenWeight(Long min, Long max);
}

@RequiredArgsConstructor
class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression productIdEq(Long id){
        return id != null ? product.productId.eq(id) : null;
    }

    private BooleanExpression productNameEq(String name){
        return name != null ? product.productName.eq(name) : null;
    }

    @Override
    public List<Product> findByContentNumb(Long numb) {

//        List<ProductContent> productContents = jpaQueryFactory.selectFrom(c)
//                .join(c.product, p)
//                .where(p.productName.contains("맥북"))
//                .fetch();

//        long count = jpaQueryFactory.select(QProductContent.productContent)
//                .from(QProductContent.productContent)

//        return jpaQueryFactory.select(QProduct.product)
//                .from(QProduct.product)
//                .where()
        return null;
    }

    @Override
    public List<Product> findByProductIdFetchImpl(Long id) {

        return jpaQueryFactory.selectFrom(product)
                .join(product.productContents)
                .join(product.seller).fetchJoin()
                .where(productIdEq(id))
                .fetch();
    }

    @Override
    public List<Product> findByProductIdWoFetchImpl(Long id) {

        return jpaQueryFactory.selectFrom(product)
                .join(product.productContents)
                .join(product.seller)
                .where(productIdEq(id))
                .fetch();
    }

    @Override
    public List<Product> findByProductName(String name) {
        return jpaQueryFactory.selectFrom(product)
                .where(productNameEq(name))
                .fetch();
    }

    @Override
    public List<Product> findBetweenWeight(Long min, Long max) {

        return jpaQueryFactory.selectFrom(product)
                .where(product.weight.between(min, max))
                .fetch();
    }
}


public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{

    // 일반적 join 사용
    @Query("SELECT p FROM Product p LEFT JOIN Seller s ON p.seller.sellerId = s.sellerId WHERE s.sellerName = :name ")
    Product findBySellerName(@Param("name") String name);

    // Fetch join 사용
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.seller ps " +
            "LEFT JOIN FETCH p.productContents " +
            "WHERE p.productId = :id")
    Product findByProductIdWithFetch(@Param("id") Long id);

    @Query(value = "SELECT distinct p FROM Product p " +
            "LEFT JOIN FETCH p.seller ps " +
            "LEFT JOIN FETCH p.productContents ")
    List<Product> findAllProduct();


    Product findByProductId(Long id);

    Page<Product> findAll(Pageable pageable);

    // 1+N 문제 발생
    @Query("SELECT  p FROM Product p WHERE p.productId = :id ")
    Product findByProductIdWOFetch(@Param("id") Long id);

}
