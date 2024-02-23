package dev.ajim.springdemo.models.learndb;

import dev.ajim.springdemo.models.learndb.types.BranchSupplierKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BranchSupplier {

    @EmbeddedId
    private BranchSupplierKey id;

    private String supplyType;

//    @MapsId("branch_id")
    @ManyToOne
    @JoinColumn(name = "branch_id", insertable = false, updatable = false)
    private Branch branchId;
}
