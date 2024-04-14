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
 * 그런데 왜 시험 로직도 롤백이 되었을까요?<br/><br/>
 * # 1 <br/>
 * ❌❌❌❌<br/>
 *  # 2<br/>
 * {@link org.springframework.transaction.annotation.Transactional}의 사용 위치는 원인이 될 수 없습니다. 그건 사용법에 관한 것이다.<br/><br/>
 *
 * `@Transactional가 메소드에 사용되면 해당 메소드의 작업 중 하나에서 오류가 발생하면 롤백된다.`<br/>
 * 이 부분에 대해 좀 더 구체적으로 설명해 주시겠어요? 선주 씨가 제대로 이해하고 있는지 알기 어려운 설명입니다.<br/><br/>
 *
 * ps.<br/>
 * 문제는 원인을 설명하는 것이기 때문에 해결 방법은 자유입니다.<br/>
 * 다만, 해결 법도 작성했으니 한 말씀 드리면, 이걸 지워서 해결하는 방식은 좋지 않습니다.<br/><br/>
 *
 * 만일 해당 프로세스에 추가 작업이 생기면 다시 선언해야 할 텐 데 그럼 원인이 해결된 게 아니겠죠?<br/>
 * 단순히 돌아 가게만 하는 것보다는, 근본적인 해결 방법을 고민해보는 것이 좋습니다.<br/>
 *
 * === 서술 ===<br/>
 * 다음을 설명해주세요.<br/>
 * - 원인은?<br/>
 *
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