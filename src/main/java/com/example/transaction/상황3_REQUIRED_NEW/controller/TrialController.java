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
 *
 * 런타임 예외 발생시 예외 처리를 하지않으면 호출한 서비스 쪽으로 예외가 던져짐
 * rollBackOnly 설정과 별개로 시험 서비스에 예외가 옮겨 오기 때문에 롤백 처리 됌
 *
 * - Propagation.REQUIRES_NEW가 필요한 상황은?
 * 내부 트랜잭션의 물리 트랜잭션을 분리하고 싶을때
 * 상대적으로 메인 로직과 관계가 적거나 중요도가 낮아 내부로직의 예외로 메인로직이 롤백되게 하고싶지 않을때 사용
 * 커넥션풀의 부하가 많이 걸리므로 사용량 및 내부 로직의 소요시간에 주의해서 사용
 *
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
