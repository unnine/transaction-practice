package com.example.transaction.상황5_Transactional.controller;

import com.example.transaction.상황5_Transactional.service.TrialService;
import com.example.transaction.상황5_Transactional.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 5.<br/><br/>
 *
 * 승인 처리하는 메서드에 {@link org.springframework.transaction.annotation.Transactional}이 선언되어 있습니다.<br/>
 * 그런데, 서비스에서 에러가 발생했는데 승인 처리가 롤백되지 않았습니다.<br/><br/>
 *
 * ※ IDE에서 오류를 표시해주는 밑줄은 확인하지 않고 푸는 것을 권장드립니다.<br/><br/>
 *
 * # 1<br/>
 * `@Transactional`이 AOP로 처리하기 때문에 발생하는 문제이다. 즉, AOP의 특성과 관련이 있다.<br/><br/>
 *
 * === 서술 ===<br/>
 * - 원인<br/>
 * AOP 내부 참조 문제
 * - 해결방법<br/>
 * 별도의 클래스를 만들어 AOP가 적용할 수 있도록한다.<br/>
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