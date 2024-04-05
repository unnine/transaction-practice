package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.ApprovalDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.ApprovalService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;
    private final TransactionTemplate transactionTemplate;

    @Override
    public void approve(ApprovalVO vo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    approvalDao.create(vo);
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                }
            }
        });
    }
}
