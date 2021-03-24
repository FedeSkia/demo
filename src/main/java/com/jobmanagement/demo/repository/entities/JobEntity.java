package com.jobmanagement.demo.repository.entities;

import com.jobmanagement.demo.domain.JobState;
import liquibase.pro.packaged.I;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

}
