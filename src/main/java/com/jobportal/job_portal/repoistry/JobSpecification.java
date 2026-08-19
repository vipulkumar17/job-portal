package com.jobportal.job_portal.repoistry;

import com.jobportal.job_portal.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification <Job>  hasTitle(String title){
        return(root,query,cb)->{
            if(title==null || title.isBlank()){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("title")),"%"+ title.toLowerCase()+ "%");
        };

    }

    public static Specification<Job> hasLocation(String location){
        return (root,query,cb)->{
            if(location==null || location.isBlank()){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("location")), "%"+ location.toLowerCase()+"%");
        };
    }
    public static Specification<Job> salaryBetween(Double minSalary, Double maxSalary){
        return(root,query,cb)->{
            if(minSalary==null && maxSalary==null){
                return cb.conjunction();
            }
            if(minSalary!=null && maxSalary!=null){
                return cb.between(root.get("salary"),minSalary,maxSalary );


            }
            if(minSalary!=null){
                return cb.greaterThanOrEqualTo(root.get("salary"),minSalary);
            }
            return cb.lessThanOrEqualTo(root.get("salary"),maxSalary);
        };
    }

}
    

