package com.info5059.casestudy.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PurchaseOrderController {
        @Autowired
        private PurchaseOrderDAO purchaseOrderDAO;
        @PostMapping("/api/pos")
        public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder productrep) { // use RequestBody here
            Long purchaseOrderid = purchaseOrderDAO.create(productrep);
            return new ResponseEntity<Long>(purchaseOrderid, HttpStatus.OK);
        }

    @GetMapping("/api/pos/{id}")
    public ResponseEntity<Iterable<PurchaseOrder>> findByVendorId(@PathVariable Long id){
        Iterable<PurchaseOrder> vendorPurchaseOrder = purchaseOrderDAO.findByVendor(id);
        return new ResponseEntity<Iterable<PurchaseOrder>>(vendorPurchaseOrder, HttpStatus.OK);
    }
}
