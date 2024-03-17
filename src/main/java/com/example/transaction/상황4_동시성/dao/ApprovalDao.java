package com.example.transaction.상황4_동시성.dao;

import com.example.transaction.상황4_동시성.vo.ApprovalVO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalDao {

    @Insert("INSERT INTO APPROVAL ( ... ) VLAUES ( ... )")
    int create(ApprovalVO vo);

}
