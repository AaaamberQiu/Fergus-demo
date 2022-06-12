package com.fergus.demo.repo;

import com.fergus.demo.model.Job;
import com.fergus.demo.model.Note;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NoteSpecification implements Specification<Note> {

    private final long jobId;

    public NoteSpecification(long jobId){
        this.jobId = jobId;
    }

    @Override
    public Predicate toPredicate(Root<Note> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("jobId"), jobId);
    }
}
