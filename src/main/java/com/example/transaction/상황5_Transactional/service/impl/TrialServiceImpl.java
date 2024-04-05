package com.example.transaction.상황5_Transactional.service.impl;

import com.example.transaction.상황5_Transactional.dao.TrialDao;
import com.example.transaction.상황5_Transactional.service.ApprovalService;
import com.example.transaction.상황5_Transactional.service.TrialService;
import com.example.transaction.상황5_Transactional.vo.ApprovalVO;
import com.example.transaction.상황5_Transactional.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalService approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        approve(param.getApprovalVO());
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        approve(param.getApprovalVO());
        return param;
    }

    public void approve(ApprovalVO vo) {
        approvalService.approve(vo);
    }
}