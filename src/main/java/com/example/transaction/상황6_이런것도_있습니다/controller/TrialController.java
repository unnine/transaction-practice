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
 *   시험로직과 시험로직이 같은 트렌잭션을 사용하기 있기 때문입니다.
 *   승인 로직에서 예외를 try-catch로 잡아도 신규 트랜잭션이 아니기 때문에 트랜잭션에 rollbackOnly를 표시합니다.
 *   그래서 시험 로직에서 트랜잭션 커밋을 하려 해도 트랜잭션이 rollbackOnly가 체크되어 UnexpectedRollbackException가 발생되고 롤백됩니다.
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