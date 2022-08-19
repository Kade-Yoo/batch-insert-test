package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.dao.BatchDao;
import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JpaBatchInsertServiceImplTest {

    @Autowired
    private JpaBatchInsertService jpaBatchInsertService;

    @Test
    void batchInsert() {
        List<Order> orders = jpaBatchInsertService.batchInsert();
        assertEquals(10000, orders.size());
    }
}