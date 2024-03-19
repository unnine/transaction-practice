package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 상황해석1. Service에 transactional 모두 적용되어 있지 않음.
 * 상황해석2. 모든 곳에 트랜잭션이 적용되어 있지 않음.
 *
 * 적용사항1. trialDao.create(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 * 적용사항2. trialDao.update(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 *
 * 해결방안
 * 1) 트랜잭션을 적용 후, 트랜잭션 분리
 *
 */
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
