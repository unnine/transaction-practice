package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;
    private final TransactionTemplate transactionTemplate;

    @Override
    public TrialVO startTrial(TrialVO param) {
        start(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        update(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    private void start(TrialVO vo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    trialDao.create(vo);
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                }
            }
        });
    }

    private void update(TrialVO vo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    trialDao.update(vo);
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                }
            }
        });
    }
}
