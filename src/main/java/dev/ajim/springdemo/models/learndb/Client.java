package dev.ajim.springdemo.models.learndb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    private Integer id;

    private String clientName;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branchId;
}

