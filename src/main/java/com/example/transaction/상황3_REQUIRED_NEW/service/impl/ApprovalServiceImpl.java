package com.example.transaction.상황3_REQUIRED_NEW.service.impl;

import com.example.transaction.상황3_REQUIRED_NEW.dao.ApprovalDao;
import com.example.transaction.상황3_REQUIRED_NEW.service.ApprovalService;
import com.example.transaction.상황3_REQUIRED_NEW.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void approve(ApprovalVO vo) {
        try{
            approvalDao.create(vo);
        }catch (RuntimeException e) {
            log.info("예외 발생");
        }
    }
}
