package com.fergus.demo.vo;

import com.fergus.demo.model.Client;
import com.fergus.demo.model.Note;
import lombok.Data;

import java.util.List;

@Data
public class JobVO {

    private long id;

    private String status;

    private Long createTime;

    private Client client;

    private List<Note> notes;
}
