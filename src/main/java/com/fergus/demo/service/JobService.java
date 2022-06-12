package com.fergus.demo.service;

import com.fergus.demo.convertor.JobConvertor;
import com.fergus.demo.model.Client;
import com.fergus.demo.model.Job;
import com.fergus.demo.model.Note;
import com.fergus.demo.repo.*;
import com.fergus.demo.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ClientRepository clientRepository;


    public void init(){
        Client client1 = clientRepository.save(new Client("Amy", "111"));
        Client client2 = clientRepository.save(new Client("Tome", "222"));

        Job job1 = new Job();
        job1.setClientId(client1.getId());
        job1.setCreateTime(System.currentTimeMillis());
        job1.setStatus("active");
        jobRepository.save(job1);

        Job job2 = new Job();
        job2.setClientId(client1.getId());
        job2.setCreateTime(System.currentTimeMillis());
        job2.setStatus("scheduled");
        jobRepository.save(job2);


        Job job3 = new Job();
        job3.setClientId(client2.getId());
        job3.setCreateTime(System.currentTimeMillis());
        job3.setStatus("completed");
        jobRepository.save(job3);
    }


    public List<JobVO> queryAllJobs(){
        List<Job> res = jobRepository.findAll();
        return res.stream()
                .map(JobConvertor::convert2VO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public List<JobVO> queryByConditions(String status, Long afterTime){
        Job filter = new Job();
        filter.setStatus(status);
        filter.setCreateTime(afterTime);

        List<Job> res = jobRepository.findAll(new JobSpecification(filter));
        return res.stream()
                .map(JobConvertor::convert2VO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public JobVO queryById(Long jobId){
        Optional<Job> res = jobRepository.findById(jobId);
        if(res.isPresent()){
            JobVO ret = JobConvertor.convert2VO(res.get());
            List<Note> notes = noteRepository.findAll(new NoteSpecification(jobId));
            Client client = clientRepository.findById(res.get().getClientId()).orElse(null);
            ret.setNotes(notes);
            ret.setClient(client);
            return ret;
        }
        return null;
    }


    public void updateStatus(Long jobId, String newStatus){
        Optional<Job> res = jobRepository.findById(jobId);
        if(res.isPresent()){
            Job prev = res.get();
            prev.setStatus(newStatus);
            jobRepository.save(prev);
        }
    }

    public Note getNote(Long noteId){
        return noteRepository.findById(noteId).orElse(null);
    }


    public Long addNote(Long jobId, String content){
        Note note = noteRepository.save(new Note(jobId, content));
        return note.getId();
    }


    public void updateNote(Long noteId, String content){
        Optional<Note> res = noteRepository.findById(noteId);
        if(res.isPresent()){
            Note prev = res.get();
            prev.setContent(content);
            noteRepository.save(prev);
        }
    }
}
