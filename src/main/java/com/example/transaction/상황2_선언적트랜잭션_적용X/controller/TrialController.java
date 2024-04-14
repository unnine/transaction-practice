package com.example.transaction.상황2_선언적트랜잭션_적용X.controller;

import com.example.transaction.상황2_선언적트랜잭션_적용X.service.TrialService;
import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 문제 2.
 *
 * service 패키지에 선언적 트랜잭션 AOP가 적용되어 있지 않습니다.
 *
 * # 2<br/>
 * 불필요한 코드 삭제<br/><br/>
 *
 * 요구 사항은 트랜잭션을 분리하는 것입니다.<br/>
 * 그렇다면 트랜잭션이 정확히 무엇인지 알아야겠죠?<br/>
 *
 * 작업의 단위같이 논리적이고 개념적인 부분이 아닌, 트랜잭션이 물리적으로 어떻게 구현되어 있는지 정확하게 파악하라.<br/>
 *
 * === 소스 수정 ===<br/>
 * 1. 시험 시작 로직과 결재 로직의 트랜잭션을 분리해주세요.<br/>
 * 2. 시험 수정 로직과 결재 로직의 트랜잭션을 분리해주세요.<br/>
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
