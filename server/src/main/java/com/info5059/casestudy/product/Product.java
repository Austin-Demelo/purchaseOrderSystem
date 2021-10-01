package com.info5059.casestudy.product;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    private String Id;
    private int vendorid;
    private String name;
    private BigDecimal costprice; //make this lower than msrp
    private BigDecimal msrp;
    private int rop; //reorder point
    private int eoq; //economic order quanity
    private int qoh; //what we have in inventory
    private int qoo; //what we have ordered not recieved
    @Lob
    @Basic(optional = true)
    private byte[] qrcode;
    private String qrcodetxt; //case 2
}
