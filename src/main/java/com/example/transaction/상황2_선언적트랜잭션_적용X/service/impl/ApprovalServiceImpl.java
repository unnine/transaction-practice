package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.ApprovalDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.ApprovalService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    public void approve(ApprovalVO vo) {
        try{
            approvalDao.create(vo);
        } catch(Exception e){
            log.info("Error ====> 승인 요청 중 오류:", e);
        }

    }
}
