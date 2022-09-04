package kr.kade.batchinserttest.dao;

import kr.kade.batchinserttest.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

@RequiredArgsConstructor
public class MybatisBatchDaoImpl {

    private final SqlSessionFactory sqlSessionFactory;

    public int batchInsert(List<OrderDto> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        int result = 0;

        try {
            result = sqlSession.insert("kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper", list);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }  finally {
            sqlSession.flushStatements();
            sqlSession.close();
        }

        return result;
    }
}
