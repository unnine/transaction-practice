package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;
    private final PlatformTransactionManager txManager;

    @Override
    public TrialVO startTrial(TrialVO param) {
        create(param);
        approve(param);
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        update(param);
        approve(param);
        return param;
    }

    private void create(TrialVO param) {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        try {
            trialDao.create(param);
        } catch (RuntimeException e) {
            txManager.rollback(status);
        }
        txManager.commit(status);
    }

    private void update(TrialVO param) {
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());
        try {
            trialDao.create(param);
        } catch (RuntimeException e) {
            txManager.rollback(status);
        }
        txManager.commit(status);
    }

    private void approve(TrialVO param) {
        approvalService.approve(param.getApprovalVO());
    }
}
