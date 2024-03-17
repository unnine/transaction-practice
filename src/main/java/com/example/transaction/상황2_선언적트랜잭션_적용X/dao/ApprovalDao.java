package com.example.transaction.상황2_선언적트랜잭션_적용X.dao;

import com.example.transaction.상황2_선언적트랜잭션_적용X.vo.ApprovalVO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalDao {

    @Insert("INSERT INTO APPROVAL ( ... ) VLAUES ( ... )")
    int create(ApprovalVO vo);

}
