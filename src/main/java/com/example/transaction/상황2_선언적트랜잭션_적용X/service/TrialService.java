package com.example.transaction.상황2_선언적트랜잭션_적용X.service;

import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;

public interface TrialService {

    TrialVO startTrial(TrialVO param);

    TrialVO updateTrialInfo(TrialVO param, Integer idx);

}
