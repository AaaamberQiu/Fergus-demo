package com.fergus.demo.convertor;

import com.fergus.demo.model.Job;
import com.fergus.demo.vo.JobVO;

public class JobConvertor {

    public static JobVO convert2VO(Job job){
        if(job == null){
            return null;
        }
        JobVO vo = new JobVO();
        vo.setCreateTime(job.getCreateTime());
        vo.setStatus(job.getStatus());
        vo.setId(job.getId());
        return vo;
    }
}
