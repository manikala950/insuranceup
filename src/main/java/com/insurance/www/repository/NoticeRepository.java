package com.insurance.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.www.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
