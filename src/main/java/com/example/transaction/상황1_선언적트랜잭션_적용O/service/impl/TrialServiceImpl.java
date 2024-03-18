package com.example.transaction.상황1_선언적트랜잭션_적용O.service.impl;

import com.example.transaction.상황1_선언적트랜잭션_적용O.dao.TrialDao;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.TrialService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.service.ApprovalService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
