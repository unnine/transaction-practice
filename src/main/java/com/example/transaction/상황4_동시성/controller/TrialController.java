package com.example.transaction.상황4_동시성.controller;

import com.example.transaction.상황4_동시성.service.TrialService;
import com.example.transaction.상황4_동시성.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 4.<br/><br/>
 *
 * service 패키지에 선언적 트랜잭션 AOP가 적용되어 있다고 합니다.<br/>
 * 그리고 서버는 2명 이상의 사용자로부터 거의 동시에 시험 시작 요청을 받았습니다.<br/>
 *
 * # 1<br/>
 *  - 갱신 손실이 아니다.<br/>
 *      갱신 손실은 흔히 일어나는 일이지만 특정한 상환이 아니라면 문제가 되지 않는다.<br/>
 *      또한 해결 방법도 지금 코드에서 어떻게 처리하면 되는지 알아보자.<br/><br/>
 *
 * === 서술 ===<br/>
 * 다음을 설명해주세요.<br/>
 * - 발생 가능한 문제<br/>
 * 데이터베이스의 무결성을 위반한다.
 * 
 * - 해결법<br/>
 * 낙관적 락을 사용해 데이터베이스 무결성 위반을 해결할 수 있다.<br/>
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
