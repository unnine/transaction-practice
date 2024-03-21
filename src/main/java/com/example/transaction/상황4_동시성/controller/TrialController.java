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
 * 1) 데이터 일관성 문제
 * 원인: 동시에 여러 사용자가 서비스를 호출하면, 서비스 메서드가 병렬적으로 실행된다. 이 때, 서로 다른 트랜잭션 내에서 동일한 데이터에 대한 접근이 발생될 수 있다.
 * 이로 인해 경쟁(경합) 조건이 발생되어 데이터 일관성이 깨질 수 있다.
 *
 * - 해결법
 * 1) 트랜잭션 격리 수준 컨트롤
 * 원리: 트랜잭션의 격리 수준을 높여, 동시성을 감소시킬 수 있다.
 * 격리 수준:
 * - READ_UNCOMMITTED: 커밋 안된 데이터 읽음 허용
 * - READ_COMMITTED: 커밋된 데이터만 읽음 허용(Default)
 * - REPEATABLE_READ: 트랜잭션이 시작될 때 읽은 데이터는 해당 트랜잭션이 종료될 때까지 일관성 유지
 * - SERIALIZABLE: 동시성 차단하여 트랜잭션 순차 실행
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
