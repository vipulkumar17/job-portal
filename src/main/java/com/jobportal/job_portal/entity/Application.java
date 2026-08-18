package com.jobportal.job_portal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name="applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Application{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="job_id" ,nullable=false)
    private Job job;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false )
    private User user;

    private String resume;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status=Status.APPLIED;

    private LocalDateTime appliedAt=LocalDateTime.now();

    public enum Status{
        APPLIED,SHORTLISTED,REJECTED
    }

    

}
