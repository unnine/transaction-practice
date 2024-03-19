package com.example.transaction.상황3_REQUIRED_NEW.service.impl;

import com.example.transaction.상황3_REQUIRED_NEW.dao.TrialDao;
import com.example.transaction.상황3_REQUIRED_NEW.service.TrialService;
import com.example.transaction.상황3_REQUIRED_NEW.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch(Exception e){
            log.info("승인 오류 발생 / 처리 로직", e);
        }
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch(Exception e){
            log.info("승인 오류 발생 / 처리 로직", e);
        }
        return param;
    }
}
