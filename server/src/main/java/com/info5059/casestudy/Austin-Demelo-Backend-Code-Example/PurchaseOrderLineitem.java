package com.info5059.casestudy.order;


import lombok.Data;
import lombok.RequiredArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor

public class PurchaseOrderLineitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long poid;
    private String productid;
    private int qty;
    private BigDecimal price;
}
