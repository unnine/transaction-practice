package com.example.transaction.상황5_Transactional.controller;

import com.example.transaction.상황5_Transactional.service.TrialService;
import com.example.transaction.상황5_Transactional.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 1.
 *
 * 승인 처리하는 메서드에 @Transactional이 선언되어 있습니다.
 * 그런데, 서비스에서 에러가 발생했는데 승인 처리가 롤백되지 않았습니다.
 *
 * ※ IDE에서 오류를 표시해주는 밑줄은 확인하지 않고 푸는 것을 권장드립니다.
 *
 * === 서술 ===
 * - 원인
 * - 자바에서는 기본적으로 this가 생략되어져 있는데 프록시 객체가 아닌 실제 객체에서 트랜잭션이 걸린 메소드를 호출해도 이미 실제 객체 내부에 있기 때문에 트랜잭션 적용이 안된다.
 *
 * - 해결방법
 * - 정답 : 시험 시작, 수정 메서드에 트랜잭션을 적용시킴
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

}