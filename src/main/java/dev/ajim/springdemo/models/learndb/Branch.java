package dev.ajim.springdemo.models.learndb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue
    private Integer id;

    private String branchName;

    @OneToOne
    @JoinColumn(name = "mgr_id")
    private Employee mgrId;

    private LocalDate mgrStartDate;
}
