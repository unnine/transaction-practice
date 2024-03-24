package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.TrialDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 상황해석1. Service에 transactional 모두 적용되어 있지 않음.
 * 상황해석2. 모든 곳에 트랜잭션이 적용되어 있지 않음.
 *
 * 적용사항1. trialDao.create(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 * 적용사항2. trialDao.update(param)과 approvalService.approve(); 메소드가 트랜잭션 분리되어 동작하도록 코드 수정
 *
 * 해결방안
 * 1) 트랜잭션 적용 후 approve에 REQUIRES_NEW 선언하여 트랜잭션 분리
 *
 *  === 24.03.21 피드백 ===
 *  - 내용: 불필요한 코드 제거
 *  - 원인: annotation 추가에서 불필요한 코드 제거... 뭘까....
 *
 * 2차 해결방안
 * 1) @Transactional 사용없이 해결하는 방식으로 접근 (@Transactional이 트랜잭션이 아님)
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TrialServiceImpl implements TrialService {

    private final TrialDao trialDao;
    private final ApprovalServiceImpl approvalService;

    @Override
    public TrialVO startTrial(TrialVO param) {
        trialDao.create(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch(RuntimeException e){
            log.info("Error ====> 승인 요청 중 오류:", e);
        }
        return param;
    }

    @Override
    public TrialVO updateTrialInfo(TrialVO param, Integer idx) {
        trialDao.update(param);
        try{
            approvalService.approve(param.getApprovalVO());
        } catch(RuntimeException e){
            log.info("Error ====> 승인 요청 중 오류:", e);
        }

        return param;
    }
}
