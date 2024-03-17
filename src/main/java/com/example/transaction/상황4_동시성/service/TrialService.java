package com.example.transaction.상황4_동시성.service;

import com.example.transaction.상황4_동시성.vo.TrialVO;

public interface TrialService {

    TrialVO startTrial(TrialVO param);

    TrialVO updateTrialInfo(TrialVO param, Integer idx);

}
