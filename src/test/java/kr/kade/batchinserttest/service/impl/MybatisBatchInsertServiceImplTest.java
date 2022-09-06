package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.service.MybatisBatchInsertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MybatisBatchInsertServiceImplTest {

    @Autowired
    private MybatisBatchInsertService mybatisBatchInsertService;

    @Test
    @DisplayName("Mybatis 건별 10000건 insert")
    void 단건_배치_저장() {
        List<OrderRequest> testCase = createTestCase();
        int saveCount = mybatisBatchInsertService.singleBatchSave(testCase);
        assertEquals(10000, saveCount);
    }

    @Test
    @DisplayName("Mybatis 멀티 10000건 insert")
    void 멀티_배치_저장() {
        List<OrderRequest> testCase = createTestCase();
        int saveCount = mybatisBatchInsertService.multiBatchSave(testCase);
        assertEquals(10000, saveCount);
    }

    List<OrderRequest> createTestCase() {
        List<OrderRequest> orders = new ArrayList<>();
        for (long i = 0; i < 10000; i++) {
            orders.add(OrderRequest.builder()
                    .orderType("COMPLETE").productNm("Product" + i + 3)
                    .build());
        }
        return orders;
    }
}