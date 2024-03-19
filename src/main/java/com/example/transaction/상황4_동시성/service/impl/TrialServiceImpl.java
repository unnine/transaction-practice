package com.example.transaction.상황4_동시성.service.impl;

import com.example.transaction.상황4_동시성.dao.TrialDao;
import com.example.transaction.상황4_동시성.service.ApprovalService;
import com.example.transaction.상황4_동시성.service.TrialService;
import com.example.transaction.상황4_동시성.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalService approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch(Exception e){
            System.out.println("예외처리" + e);
        }
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        approvalService.approve(param.getApprovalVO());
        trialDao.update(param);
        return param;
    }
}