package dev.ajim.springdemo.models.learndb.types;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class BranchSupplierKey implements Serializable {

    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "supplier_name")
    private String supplierName;
}
