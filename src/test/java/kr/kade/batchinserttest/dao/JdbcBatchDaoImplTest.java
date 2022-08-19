package kr.kade.batchinserttest.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class JdbcBatchDaoImplTest {

    @Autowired
    private BatchDao batchDao;

    @Test
    void batchInsertForOrder() {
        int[] ints = batchDao.batchInsertForOrder();
        assertEquals(10000, ints.length);
    }
}