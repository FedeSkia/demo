package com.jobmanagement.demo.repository.entities;

import com.jobmanagement.demo.domain.JobState;
import liquibase.pro.packaged.I;
import lombok.*;

import javax.persistence.*;

@Table(name = "job")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
