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

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;

    @Override
    @Transactional
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }

    @Override
    @Transactional
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        approvalService.approve(param.getApprovalVO());
        return param;
    }
}

