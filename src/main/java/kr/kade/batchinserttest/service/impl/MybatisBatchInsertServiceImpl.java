package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import kr.kade.batchinserttest.model.OrderDto;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MybatisBatchInsertServiceImpl implements MybatisBatchInsertService {

    private final MybatisBatchInsertMapper mybatisBatchInsertMapper;

    @Override
    @ProcessTimeLogging
    public int save() {
        for (long i = 0; i < 100000; i++) {
            mybatisBatchInsertMapper.batchInsert(new OrderDto("Product" + i + 3, "COMPLETE"));
        }

        return 0;
    }
}
