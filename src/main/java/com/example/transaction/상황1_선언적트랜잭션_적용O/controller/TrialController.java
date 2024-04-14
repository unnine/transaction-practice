package com.example.transaction.상황1_선언적트랜잭션_적용O.controller;

import com.example.transaction.상황1_선언적트랜잭션_적용O.service.TrialService;
import com.example.transaction.상황1_선언적트랜잭션_적용O.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 1.<br/><br/>
 *
 * service 패키지에 선언적 트랜잭션 AOP가 적용되어 있다고 합니다.<br/><br/>
 *
 * # 1 <br/>
 * 트랜잭션 분리가 이루어지지 않음<br/>
 *
 * # 2<br/>
 * 위 상황에서 트랜잭션을 `REQUIRES_NEW`로 나누면 예외가 외부로 전파되기 때문에 예외 처리를 해야 한다.<br/><br/>
 *
 * 하지만, 그렇게 되면 `ControllerAdvice`를 활용하지 못하게 되어 확장성이 떨어진다.<br/>
 * 따라서, 이를 처리하기 위해 코드는 불필요하게 더욱 복잡해지게 됩니다.<br/><br/>
 *
 * 분명 가능한 방법이지만 위 상황에서는 권장하지 않는 안티 패턴입니다.<br/><br/>
 *
 * 더욱 간단하고 깔끔한 방법이 있습니다. 조금 더 고민해볼까요?<br/><br/>
 *
 * === 소스 수정 ===<br/>
 * 1. 시험 시작 로직과 결재 로직의 트랜잭션을 분리해주세요.<br/>
 * 2. 시험 수정 로직과 결재 로직의 트랜잭션을 분리해주세요.<br/>
 */

@RestController
@RequestMapping("/api/v1/trail")
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
