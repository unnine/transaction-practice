package com.example.transaction.상황6_이런것도_있습니다.controller;

import com.example.transaction.상황6_이런것도_있습니다.service.TrialService;
import com.example.transaction.상황6_이런것도_있습니다.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 6.
 *
 * Approve에서 예외가 발생해도 시험 로직으로 예외가 전파되지 않도록 예외 처리를 했습니다.
 *
 * 그런데 승인 로직에서 예외가 발생하자 시험 로직도 롤백되어 버렸습니다.
 * 분명, 분명 예외가 전파되지 않도록 try-catch로 처리해놨기에 예외는 승인 로직에서 마무리되었습니다.
 * 그런데 왜 시험 로직도 롤백이 되었을까요?
 *
 * === 서술 ===
 * 다음을 설명해주세요.
 * - 원인은?
 * approve 메서드는 신규 트랜잭션이 아니기 때문에
 * 해당 메서드에서 발생된 예외로 인하여 트랜잭션 동기화 매니저에 rollbackOnly가 설정되어 시험 로직 또한 롤백되게 된다.
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