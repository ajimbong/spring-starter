package dev.ajim.springdemo.models.learndb.types;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class WorksWithKey implements Serializable {
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "client_id")
    private Integer clientId;
}
