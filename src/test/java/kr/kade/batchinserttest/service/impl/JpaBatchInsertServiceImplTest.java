package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
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
class JpaBatchInsertServiceImplTest {

    @Autowired
    private JpaBatchInsertService jpaBatchInsertService;

    @Test
    @DisplayName("JPA 10000건 insert")
    void batchInsert() {
        List<OrderRequest> testCase = createTestCase();
        int count = jpaBatchInsertService.batchInsert(testCase);
        assertEquals(10000, count);
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