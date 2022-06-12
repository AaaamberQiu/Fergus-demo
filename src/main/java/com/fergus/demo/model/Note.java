package com.fergus.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "content")
    private String content;

    public Note(long jobId, String content){
        this.jobId = jobId;
        this.content = content;
    }
}
