package com.example.transaction.상황1_선언적트랜잭션_적용O.service.impl;

import com.example.transaction.상황1_선언적트랜잭션_적용O.dao.TrialDao;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.TrialService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.ApprovalService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalService approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        createTrial(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        updateTrial(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createTrial(TrialVO param){
        trialDao.create(param);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTrial(TrialVO param){
        trialDao.update(param);
    }
}
