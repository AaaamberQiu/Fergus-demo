package com.fergus.demo.controller;

import com.fergus.demo.model.Note;
import com.fergus.demo.service.JobService;
import com.fergus.demo.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobService jobService;


    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public ResponseEntity init(){
        jobService.init();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<JobVO>> getJobList(@RequestParam(required = false) String status, @RequestParam(required = false) Long afterTime){
        return new ResponseEntity<>(jobService.queryByConditions(status, afterTime), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<JobVO> getJobById(@RequestParam Long id){
        JobVO job = jobService.queryById(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(jobService.queryById(id), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-note")
    public ResponseEntity<Long> addNote(@RequestParam Long jobId, @RequestBody String note){
        try{
            Long noteId = jobService.addNote(jobId, note);
            return new ResponseEntity<>(noteId, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update-note")
    public ResponseEntity updateNote(@RequestParam Long noteId, @RequestBody String note){
        try{
            Note prev = jobService.getNote(noteId);
            if(prev == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            jobService.updateNote(noteId, note);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update-status")
    public ResponseEntity updateStatus(@RequestParam Long jobId, @RequestParam String newStatus){
        try{
            JobVO prev = jobService.queryById(jobId);
            if(prev == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            jobService.updateStatus(jobId, newStatus);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
