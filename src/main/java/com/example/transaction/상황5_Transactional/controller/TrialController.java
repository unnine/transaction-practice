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
 *  트랜잭션 AOP은 프록시 객체를 생성해서 트랜잭션을 처리하는 방식을 사용합니다
 *  트랜잭션이 걸려있는경우엔 트랜잭션이 적용된 프록시 객체를 생성하지만
 *  트랜잭션이 걸려있지 않는 경우에는 프록시객체가 곧바로 원본 객체를 호출합니다.
 *  이번 상황에서는 컨트롤러가 호출한 메서드에 트랜잭션이 적용되어 있지 않아 원본 객체를 호출
 *  원본 객체에서 동일 클래스 내부의 메서드를 호출할 땐 this.가 생략된 자신의 메서드를 호출합니다.
 *  트랜잭션이 적용된 프록시 객체가 아닌 원본객체 내부에서 호출을 하기때문에 트랜잭션 어노테이션이 있더라도 적용X
 *
 * - 해결방법
 *   클래스 분리
 *
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