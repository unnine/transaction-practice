package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final ApprovalServiceImpl approvalService;
    private final InnerTrial innerTrial;

    @Override
    public TrialVO startTrial(TrialVO param) {
        innerTrial.createTrial(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        innerTrial.updateTrial(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Service
    @RequiredArgsConstructor
    static class InnerTrial{

        private final TrialDao trialDao;

        @Transactional
        public void createTrial(TrialVO param){
            trialDao.create(param);
        }

        @Transactional
        public void updateTrial(TrialVO param){
            trialDao.update(param);
        }
    }
}
