package com.example.transaction.상황4_동시성.controller;

import com.example.transaction.상황4_동시성.service.TrialService;
import com.example.transaction.상황4_동시성.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 4.
 *
 * service 패키지에 선언적 트랜잭션 AOP가 적용되어 있다고 합니다.
 * 그리고 서버는 2명 이상의 사용자로부터 거의 동시에 시험 시작 요청을 받았습니다.
 *
 *
 * === 서술 ===
 * 다음을 설명해주세요.
 * - 발생 가능한 문제
 * - 해결법
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
