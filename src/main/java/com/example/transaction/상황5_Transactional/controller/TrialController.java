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
 * 클래스가 아닌 메소드에 어노테이션을 선언했기 때문이다.
 *
 * - 해결방법
 * TrialServiceImpl의 approve 메서드에 어노테이션을 사용하지 않고,
 * TrialServiceImpl 클래스에 @Tranactional 어노테이션을 사용한다
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