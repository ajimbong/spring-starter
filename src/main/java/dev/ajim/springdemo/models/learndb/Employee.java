package dev.ajim.springdemo.models.learndb;

import dev.ajim.springdemo.models.learndb.types.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;


    private String lastName;

    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Long salary;

    @ManyToOne
    @JoinColumn(name = "super_id")
    private Employee superId;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branchId;
}
