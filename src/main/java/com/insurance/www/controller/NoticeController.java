package com.insurance.www.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.insurance.www.model.Notice;
import com.insurance.www.service.NoticeService;

@RestController
@RequestMapping("/api/notice")
@CrossOrigin(origins = "http://localhost:9505")
public class NoticeController {

    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    // ADD NOTICE
    @PostMapping("/add")
    public Notice addNotice(@RequestBody Notice notice) {

        // auto-set time
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
        notice.setTime(now);

        return service.addNotice(notice);
    }

    // GET ALL NOTICES
    @GetMapping("/list")
    public List<Notice> getNotices() {
        return service.getAllNotices();
    }
}
