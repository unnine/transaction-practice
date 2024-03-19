package com.example.transaction.상황2_선언적트랜잭션_적용X.service.impl;

import com.example.transaction.상황2_선언적트랜잭션_적용X.dao.ApprovalDao;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.ApprovalService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.UncheckedIOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void approve(ApprovalVO vo) {
            approvalDao.create(vo);
    }
}
