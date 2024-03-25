package com.example.transaction.상황6_이런것도_있습니다.service;

import com.example.transaction.상황6_이런것도_있습니다.vo.TrialVO;

public interface TrialService {

    TrialVO startTrial(TrialVO param);

    TrialVO updateTrialInfo(TrialVO param, Integer idx);

}