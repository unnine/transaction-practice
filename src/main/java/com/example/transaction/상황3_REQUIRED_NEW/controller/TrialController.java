package com.example.transaction.상황3_REQUIRED_NEW.controller;

import com.example.transaction.상황3_REQUIRED_NEW.service.TrialService;
import com.example.transaction.상황3_REQUIRED_NEW.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 3.
 *
 * Service 계층에서 Propagation.REQUIRES_NEW 를 이용해 시험 로직(시작, 수정)과 승인 로직의 트랜잭션을 분리했습니다.
 *
 * 그런데 승인 로직에서 예외가 발생하자 시험 로직도 롤백되어 버렸습니다.
 * 분명, 트랜잭션을 분리했는데 왜 이런 일이 발생한 걸까요?
 *
 *
 * === 소스 수정 ===
 * 승인 로직에서 예외가 발생해도 시험 로직은 롤백되지 않도록 수정해주세요.
 *
 * === 서술 ===
 * 다음을 설명해주세요.
 * - 원인은?
 *  승인 로직에서 발생한 예외를 처리하지 않아서 시험 로직으로 그 예외가 던져졌기 때문이다.
 *  - 참고: 1. https://woodcock.tistory.com/40
 *
 * - Propagation.REQUIRES_NEW가 필요한 상황은?
 * 내부 트랜잭션과 외부 트랜잭션을 분리해야할 때 필요하다.
 * - 참고: https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/tx-propagation.html
 */

@RestController
@RequestMapping("/api/v1/trial")
@RequiredArgsConstructor
public class TrialController {

    private final TrialService trialService;

    @PostMapping
    public ResponseEntity<TrialVO> startTrial(@RequestBody TrialVO param) {
        TrialVO result = trialService.startTrial(param);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<TrialVO> updateTrialInfo(@RequestBody TrialVO param, @PathVariable Integer idx) {
        TrialVO result = trialService.updateTrialInfo(param, idx);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
