package com.example.transaction.상황5_Transactional.service;

import com.example.transaction.상황5_Transactional.vo.TrialVO;

public interface TrialService {

    TrialVO startTrial(TrialVO param);

    TrialVO updateTrialInfo(TrialVO param, Integer idx);

}