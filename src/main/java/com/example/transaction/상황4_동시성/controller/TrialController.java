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
 * - 발생 가능한 문제 : 데드락에 걸림 -> 예를들어 한명의 시험을 시작하고 승인을 대기하는 도중 다른 사용자가 승인 대기상태에 들어간다면 서로 완료될때까지 기다리는 교착상태에 도달 -> 무한 대기 상태에 빠짐
 * - 해결법
 * - 순차적으로 문제 해결 -> SELECT KEY 실행 시 SELECT FOR UPDATE 구문 사용 -> 조회 시점에 DB락을 가져가서 다른 세션이 접근하지 못하도록 막음
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
