package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import kr.kade.batchinserttest.model.OrderDto;
import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MybatisBatchInsertServiceImpl implements MybatisBatchInsertService {

    private final MybatisBatchInsertMapper mybatisBatchInsertMapper;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    @ProcessTimeLogging
    public int singleBatchSave(List<OrderRequest> requests) {
        AtomicInteger count = new AtomicInteger(0);
        requests.stream().map(OrderRequest::ofOrderDto)
                .forEach(orderDto -> count.set(count.get() + mybatisBatchInsertMapper.singleInsert(orderDto)));
        return count.get();
    }

    @Override
    @ProcessTimeLogging
    public int multiBatchSave(List<OrderRequest> requests) {
        List<OrderDto> orderDtoList = requests.stream()
                .map(OrderRequest::ofOrderDto)
                .collect(Collectors.toList());

        return batchInsert(orderDtoList);
    }

    private int batchInsert(List<OrderDto> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        int result = 0;

        try {
            result = sqlSession.insert("kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper.batchInsert", list);
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
