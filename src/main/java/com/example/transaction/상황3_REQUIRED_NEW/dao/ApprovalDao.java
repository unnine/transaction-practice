package com.example.transaction.상황3_REQUIRED_NEW.dao;

import com.example.transaction.상황3_REQUIRED_NEW.vo.ApprovalVO;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalDao {

    @Insert("INSERT INTO APPROVAL ( ... ) VLAUES ( ... )")
    int create(ApprovalVO vo);

}
