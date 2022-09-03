package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import kr.kade.batchinserttest.model.OrderDto;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisBatchInsertServiceImpl implements MybatisBatchInsertService {

    private final MybatisBatchInsertMapper mybatisBatchInsertMapper;

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

        return mybatisBatchInsertMapper.batchInsert(list);
    }
}
