package com.info5059.casestudy.order;

import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;


@Component
public class PurchaseOrderDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Transactional
    public Long create(PurchaseOrder productrep) {
        PurchaseOrder realPurchaseOrder = new PurchaseOrder();
        realPurchaseOrder.setPodate(new Date());
        realPurchaseOrder.setVendorid(productrep.getVendorid());
        realPurchaseOrder.setAmount(productrep.getAmount());

        entityManager.persist(realPurchaseOrder);
        for(PurchaseOrderLineitem item : productrep.getItems()) {
            PurchaseOrderLineitem realItem = new PurchaseOrderLineitem();
            realItem.setPoid(realPurchaseOrder.getId());
            realItem.setProductid(item.getProductid());
            realItem.setPrice(item.getPrice());
            realItem.setQty(item.getQty());


            productRepository.updateQoo(realItem.getProductid(), realItem.getQty());

            entityManager.persist(realItem);
        }
        return realPurchaseOrder.getId();
    }
    public PurchaseOrder findOne(Long id) {
        PurchaseOrder po = entityManager.find(PurchaseOrder.class, id);
        if (po == null) {
            throw new EntityNotFoundException("Can't find Purchase Order for ID "
                    + id);
        }
        return po;
    }
    public Iterable<PurchaseOrder> findByVendor(Long vendorId) {
        return entityManager.createQuery("select v from PurchaseOrder v where v.vendorid = :id")
                .setParameter("id", vendorId)
                .getResultList();
    }
}
