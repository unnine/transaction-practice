package com.example.transaction.상황1_선언적트랜잭션_적용O.service;

import com.example.transaction.상황1_선언적트랜잭션_적용O.vo.TrialVO;

public interface TrialService {

    TrialVO startTrial(TrialVO param);

    TrialVO updateTrialInfo(TrialVO param, Integer idx);

    void approve(TrialVO param);

    void createTrial(TrialVO param);

    void updateTrial(TrialVO param);
}
