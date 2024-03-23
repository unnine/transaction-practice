package com.example.transaction.상황2_선언적트랜잭션_적용X.controller;

import com.example.transaction.상황2_선언적트랜잭션_적용X.service.ApprovalService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 2.
 *
 * service 패키지에 선언적 트랜잭션 AOP가 적용되어 있지 않습니다.
 *
 *
 * === 소스 수정 ===
 * 1. 시험 시작 로직과 결재 로직의 트랜잭션을 분리해주세요.
 * 2. 시험 수정 로직과 결재 로직의 트랜잭션을 분리해주세요.
 */

@RestController
@RequestMapping("/api/v1/trial")
@RequiredArgsConstructor
public class TrialController {

    private final TrialService trialService;

    private final ApprovalService approvalService;

    @PostMapping
    public ResponseEntity<TrialVO> startTrial(@RequestBody TrialVO param) {
        TrialVO result = trialService.startTrial(param);
        startApproval(param.getApprovalVO());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<TrialVO> updateTrialInfo(@RequestBody TrialVO param, @PathVariable Integer idx) {
        TrialVO result = trialService.updateTrialInfo(param, idx);
        startApproval(param.getApprovalVO());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void startApproval(@RequestBody ApprovalVO param) {
        approvalService.approve(param);
    }

}
