package com.example.transaction.상황4_동시성.service.impl;

import com.example.transaction.상황4_동시성.dao.ApprovalDao;
import com.example.transaction.상황4_동시성.service.ApprovalService;
import com.example.transaction.상황4_동시성.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    public void approve(ApprovalVO vo) {
        approvalDao.create(vo);
    }
}
