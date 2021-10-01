package com.info5059.casestudy.product;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    // will return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from Product where Id = ?1")
    int deleteOne(String productid);
    //Id used to be id, if we are having a problem with deleting that will be it
    @Modifying
    @Transactional
    @Query("update Product product set product.qoo = product.qoo + ?2 where product.Id = ?1")
    int updateQoo(String id, int qoo);


}
