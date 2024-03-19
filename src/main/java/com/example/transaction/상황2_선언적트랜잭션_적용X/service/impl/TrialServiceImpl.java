package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UncheckedIOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;

    @Override
    @Transactional
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch (UncheckedIOException e) {
            log.info("승인 예외 발생");
        }
        return param;
    }

    @Override
    @Transactional
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        try {
            approvalService.approve(param.getApprovalVO());
        } catch (UncheckedIOException e) {
            log.info("승인 예외 발생");
        }
        return param;
    }
}

