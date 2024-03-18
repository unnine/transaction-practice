package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrialServiceImplProxy implements TrialService{

    private final TrialServiceImplInner trialServiceImplInner;
    private final ApprovalServiceImpl approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialServiceImplInner.startTrial(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialServiceImplInner.updateTrialInfo(param, idx);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Service
    @RequiredArgsConstructor
    static class TrialServiceImplInner{

        private final TrialDao trialDao;

        @Transactional
        public TrialVO startTrial(TrialVO param) {
            trialDao.create(param);
            return param;
        }

        @Transactional
        public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
            trialDao.update(param);
            return param;
        }
    }
}
