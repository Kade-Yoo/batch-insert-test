package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import kr.kade.batchinserttest.model.OrderDto;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisBatchInsertServiceImpl implements MybatisBatchInsertService {

    private final MybatisBatchInsertMapper mybatisBatchInsertMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    @ProcessTimeLogging
    public int singleBatchSave() {
        int count = 0;
        for (long i = 0; i < 100000; i++) {
            mybatisBatchInsertMapper.singleInsert(new OrderDto("Product" + i + 3, "COMPLETE"));
            count++;
        }

        return count;
    }

    @Override
    @ProcessTimeLogging
    public int multiBatchSave() {
        List<OrderDto> list = new ArrayList<>();
        for (long i = 0; i < 100000; i++) {
            list.add(new OrderDto("Product" + i + 3, "COMPLETE"));
        }

        return batchInsert(list);
    }

    private int batchInsert(List<OrderDto> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        int result = 0;

        try {
            sqlSession.insert("kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper.batchInsert", list);
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
