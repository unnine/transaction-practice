package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.ApprovalDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.ApprovalService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;
    private PlatformTransactionManager txManager;

    @Override
    public void approve(ApprovalVO vo) {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        try {
            approvalDao.create(vo);
        } catch (RuntimeException e) {
            txManager.rollback(status);
        }
        txManager.commit(status);
    }
}
