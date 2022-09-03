package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MybatisBatchInsertServiceImplTest {

    @Autowired
    private MybatisBatchInsertService mybatisBatchInsertService;

    @Test
    void 단건_배치_저장() {
        int saveCount = mybatisBatchInsertService.singleBatchSave();
        assertEquals(10000, saveCount);
    }

    @Test
    void 멀티_배치_저장() {
        int saveCount = mybatisBatchInsertService.multiBatchSave();
        assertEquals(10000, saveCount);
    }
}