package kr.kade.batchinserttest.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class JdbcBatchDaoImplTest {

    @Autowired
    private BatchDao batchDao;

    @Test
    void batchInsertForOrder() {
        Map<String, Object>[] maps = getMaps();
        int[] ints = batchDao.batchInsertForOrder(maps);
        assertEquals(10000, ints.length);
    }

    private Map<String, Object>[] getMaps() {
        Map<String, Object>[] maps = new HashMap[10000];
        for (long i = 0; i < 10000; i++) {
            maps[(int) i] = new HashMap<>();
            maps[(int) i].put("order_id", i + 3);
            maps[(int) i].put("order_type", "COMPLETE");
            maps[(int) i].put("product_nm", "Product" + i + 3);
        }
        return maps;
    }
}