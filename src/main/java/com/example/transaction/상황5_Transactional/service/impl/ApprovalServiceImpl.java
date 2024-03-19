package com.example.transaction.상황5_Transactional.service.impl;

import com.example.transaction.상황5_Transactional.dao.ApprovalDao;
import com.example.transaction.상황5_Transactional.service.ApprovalService;
import com.example.transaction.상황5_Transactional.vo.ApprovalVO;
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