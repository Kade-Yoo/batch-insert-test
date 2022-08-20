package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.service.JdbcBatchInsertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JdbcBatchInsertServiceImplTest {

    @Autowired
    private JdbcBatchInsertService jdbcBatchInsertService;

    @Test
    void batchInsert() {
        Iterable<Order> orders = jdbcBatchInsertService.batchInsert();
    }
}