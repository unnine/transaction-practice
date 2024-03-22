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
 *    동일 스레드이므로 승인 로직에서 예외가 터지면 시험 로직에서도 예외가 전파되어 시험 로직도 롤백되어 버린다.
 * - Propagation.REQUIRES_NEW가 필요한 상황은?
 *    트랜잭션 중간에 별도의 트랜잭션을 생성이 필요할 경우
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
