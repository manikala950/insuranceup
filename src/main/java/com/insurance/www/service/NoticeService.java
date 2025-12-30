package com.insurance.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.www.model.Notice;
import com.insurance.www.repository.NoticeRepository;

@Service
public class NoticeService {

    private final NoticeRepository repo;

    public NoticeService(NoticeRepository repo) {
        this.repo = repo;
    }

    public Notice addNotice(Notice notice) {
        return repo.save(notice);
    }

    public List<Notice> getAllNotices() {
        return repo.findAll();
    }
}
