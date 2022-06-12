package com.fergus.demo.repo;

import com.fergus.demo.model.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JobSpecification implements Specification<Job> {

    private final Job criteria;

    public JobSpecification(Job criteria){
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(criteria.getStatus() != null){
            predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
        }

        if(criteria.getCreateTime() != null){
            predicates.add(criteriaBuilder.gt(root.get("createTime"), criteria.getCreateTime()));
        }
        return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))
                .distinct(true)
                .orderBy(criteriaBuilder.desc(root.get("createTime")))
                .getRestriction();
    }
}
