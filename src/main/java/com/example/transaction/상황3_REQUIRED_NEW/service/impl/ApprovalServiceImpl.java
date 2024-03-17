package com.example.transaction.상황3_REQUIRED_NEW.service.impl;

import com.example.transaction.상황3_REQUIRED_NEW.dao.ApprovalDao;
import com.example.transaction.상황3_REQUIRED_NEW.service.ApprovalService;
import com.example.transaction.상황3_REQUIRED_NEW.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void approve(ApprovalVO vo) {
        approvalDao.create(vo);
    }
}
