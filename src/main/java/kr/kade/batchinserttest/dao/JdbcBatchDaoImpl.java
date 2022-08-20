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
    public int[] batchInsertForOrder(Map<String, Object>[] maps) {
        simpleJdbcInsert.withTableName("table_order");
        return simpleJdbcInsert.executeBatch(maps);
    }
}
