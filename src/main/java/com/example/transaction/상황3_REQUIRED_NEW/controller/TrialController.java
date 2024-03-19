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
 *
 * - 원인은?
 * approvalService.approve();에서 발생한 예외 사항에 대한 복구 처리 로직이 없기 때문에 위의 문제 발생됨
 * approvalService.approve() 발생 예외 -> approve 트랜잭션 rollback -> 트랜잭션 AOP 예외 밖으로 전달
 * -> startTrial 메소드로 예외 전달됨 -> 그런데 처리 로직이 없음 -> 해당 트랜잭션 예외로 간주되어 rollback처리됨
 *
 * - Propagation.REQUIRES_NEW가 필요한 상황은?
 * 1) 예상되는 예외 사항에서 트랜잭션을 분리하여 로직을 처리해야할 때 필요
 * 2) 동시 사용자가 같은 로직을 요청 가능한 경우, 다른 사용자의 처리가 실패해도 정상 요청한 사용자는 처리되야할 때 필요
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
