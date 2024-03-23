package com.example.transaction.상황4_동시성.controller;

import com.example.transaction.상황4_동시성.service.TrialService;
import com.example.transaction.상황4_동시성.vo.TrialVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
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
 *    트랜잭션의 격리성(Isolation)이 깨지게 된다.
 *    격리성으로 인해 나타날 수 있는 문제점은 일반적으로 Dirty Read, Non-Repeatable Read, Phantom Read 3가지가 있다.
 *      Dirty Read : 다른 트랜잭션에 의해 수정됐지만 아직 커밋되지 않은 데이터를 읽는 것
 *      Non-Repeatable Read : 한 트랜잭션 내에서 같은 Key를 가진 Row를 두 번 Read 하였는데 그 사이에 값이 변경되거나 삭제되어 결과가 다르게 나타나는 것
 *      Phantom Read : 한 트랜잭션 내에서 같은 쿼리를 두 번 수행하였는데, 첫 번째 쿼리에서 없던 유령 레코드 (Phantom Record)가 두 번째 쿼리에서 나타나는 것
 *
 * - 해결법
 *    격리성 접근 레벨을 조절하여 해결
 *
 *    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
 *    Read Uncommitted - 커밋하지 않은 데이터를 읽을 수 있다.
 *                      (동시 처리 성능은 가장 높다. Dirty Read, Non-Repeatable Read, Phantom Read 문제 발생 O)
 *
 *    @Transactional(isolation = Isolation.READ_COMMITTED)
 *    Read Committed - 커밋이 완료된 데이터만 읽을 수 있다. 커밋 되지 않은 데이터에 대해서는 실제 DB 데이터가 아닌 Undo 로그에 있는 이전 데이터를 가져온다.
 *                     (Uncommitted Read 수준에 비해 동시 처리 성능은 떨어지나 Dirty Read 문제발생 X,Non-Repeatable Read와 Phantom Read 문제발생 O)
 *
*     @Transactional(isolation = Isolation.REPEATABLE_READ)
 *    Repeatable Read – 트랜잭션 내에서 삭제, 변경에 대해서 Undo 로그에 넣어두고 앞서 발생한 트랜잭션에 대해서는 실제 데이터가 아닌 Undo 로그에 있는 백업데이터를 읽게 한다.
 *                      (Dirty Read, Non-Repeatable Read 문제발생 X,Phantom Read 문제발생 O)
 *    @Transactional(isolation = Isolation.SERIALIZABLE)
 *
 *    Serializable Read - 한 트랜잭션에서 읽고 쓰는 레코드를 다른 트랜잭션에서는 절대 접근할 수 없다.
 *                        (가장 엄격한 격리 수준이지만 동시 처리 성능은 급격히 떨어진다.(Dirty Read, Non-Repeatable Read, Phantom Read 문제 발생X)
 *
 *    격리성 수준은 데이터베이스 전체에 적용되는 설정이므로 데이터 일관성을 위해 높은 격리성 수준을 사용하게 되면 동시성이 감소할 수 있으므로
 *    성능을 감안하여 적절한 격리성 레벨을 선택해야 한다.
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
