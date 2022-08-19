package kr.kade.batchinserttest.dao;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcBatchDaoImpl implements BatchDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Transactional
    @ProcessTimeLogging
    public int[] batchInsertForOrder() {
        simpleJdbcInsert.withTableName("table_order");
        Map<String, Object>[] maps = new HashMap[100000];
        for (long i = 0; i < 100000; i++) {
            maps[(int) i] = new HashMap<>();
            maps[(int) i].put("order_id", i + 3);
            maps[(int) i].put("order_type", "COMPLETE");
            maps[(int) i].put("product_nm", "Product" + i + 3);
        }
        return simpleJdbcInsert.executeBatch(maps);
    }
}
