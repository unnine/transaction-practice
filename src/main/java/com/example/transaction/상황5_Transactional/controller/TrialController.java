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
 * 1) @Transactional이 approve() 메서드에만 적용되어 있고, startTrial()과 updateTrialInfo() 메서드에는 @Transactional이 적용되어 있지 않음.
 * 메서드에서의 트랜잭션 처리가 제대로 이루어지지 않아 롤백이 진행되지 않음.
 *
 * - 해결방법
 * 1) @Transactional 어노테이션을 startTrial()과 updateTrialInfo() 메서드에 추가하여 트랜잭션 처리를 적용한다.
 * 2) Service에 @Transactional 선언하여 문제 해결한다.
 * 
 * === 24.03.22 피드백 ===
 * - 내용: 스프링 AOP 내부 호출과 트랜잭션 참고
 *
 * - 원인
 * 1) 트랜잭션이 적용되지 않은 시험로직 메서드에서 approve 메서드를 내부 호출하여 사용하고 있기 때문에
 * 트랜잭션이 적용되지 않은 객체가 가지고 있는 approve를 호출하여 사용하게 된다.
 * 때문에 트랜잭션이 적용되지 않아 롤백이 진행되지 않게 된다.
 *
 * - 해결방법
 * 1) 클래스를 분리하여 외부호출하여 동작하도록 코들 수정한다. (
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