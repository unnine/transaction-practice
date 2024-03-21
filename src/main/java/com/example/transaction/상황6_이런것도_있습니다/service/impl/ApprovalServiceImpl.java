package com.example.transaction.상황6_이런것도_있습니다.service.impl;

import com.example.transaction.상황6_이런것도_있습니다.dao.ApprovalDao;
import com.example.transaction.상황6_이런것도_있습니다.service.ApprovalService;
import com.example.transaction.상황6_이런것도_있습니다.vo.ApprovalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDao approvalDao;

    @Override
    @Transactional
    public void approve(ApprovalVO vo) {
        try {
            approvalDao.create(vo);
        } catch (Exception e) {
            System.out.println("예외는 여기서 끝낸다.");
        }
    }
}