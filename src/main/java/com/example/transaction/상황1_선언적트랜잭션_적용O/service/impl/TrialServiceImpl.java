package com.example.transaction.상황1_선언적트랜잭션_적용O.service.impl;

import com.example.transaction.상황1_선언적트랜잭션_적용O.dao.TrialDao;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.TrialService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.ApprovalService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 상황해석1. 모든 Service에 transactional 모두 적용되어 있음.
 * 상황해석2. 물리 트랜잭션 안에서 논리 트랜잭션은 이미 분리되어 있음.
 *
 * 적용사항1. trialDao.create(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 * 적용사항2. trialDao.update(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 *
 * 해결방안
 * 1. controller에서 로직 분리 (트랜잭션 나뉘어 들어감..)
 * 2. approvalService.approve(); 메소드에 REQUIRES_NEW 선언하여 신규 트랜잭션 생성함으로써 트랜잭션 분리
 * 3. TransactionTemplate 사용...?
 *
 */
@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        return param;
    }
}
