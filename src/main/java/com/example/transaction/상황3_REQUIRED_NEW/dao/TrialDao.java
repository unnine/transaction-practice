package com.example.transaction.상황3_REQUIRED_NEW.dao;

import com.example.transaction.상황3_REQUIRED_NEW.vo.TrialVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface TrialDao {

    @SelectKey(
            statement = "SELECT NVL(MAX(IDX), 0) + 1 FROM TRIAL",
            keyProperty = "idx",
            before = true,
            resultType = Integer.class
    )
    @Insert("INSERT INTO TRIAL (IDX, ...) VALUES (#{idx}, ...)")
    int create(TrialVO vo);

    @Update("UPDATE TRIAL SET ... WHERE IDX = #{idx}")
    int update(TrialVO vo);

}
