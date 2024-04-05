package com.example.transaction.상황6_이런것도_있습니다.controller;

import com.example.transaction.상황6_이런것도_있습니다.service.TrialService;
import com.example.transaction.상황6_이런것도_있습니다.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * <p>문제 6.</p>
 *
 * <p>Approve에서 예외가 발생해도 시험 로직으로 예외가 전파되지 않도록 예외 처리를 했습니다.</p>
 * <p>
 * 그런데 승인 로직에서 예외가 발생하자 시험 로직도 롤백되어 버렸습니다.
 * 분명, 분명 예외가 전파되지 않도록 try-catch로 처리해놨기에 예외는 승인 로직에서 마무리되었습니다.
 * 그런데 왜 시험 로직도 롤백이 되었을까요?
 * </p>
 * <p>
 * # 1
 * ❌❌❌❌
 * </p>
 * <p>
 * === 서술 ===<br/>
 * 다음을 설명해주세요.<br/>
 * - 원인은?<br/>
 * {@link org.springframework.transaction.annotation.Transactional}을 올바르지 않은 위치에서 사용했기 때문이다.
 * @Transactional가 메소드에 사용되면 해당 메소드의 작업 중 하나에서 오류가 발생하면 롤백된다.
 * 따라서 어노테이션의 위치를 올바른 곳으로 수정해야한다.
 * </p>
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