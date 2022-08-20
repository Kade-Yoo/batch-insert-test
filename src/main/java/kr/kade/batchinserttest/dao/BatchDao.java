package kr.kade.batchinserttest.dao;

import java.util.Map;

public interface BatchDao {

    int[] batchInsertForOrder(Map<String, Object>[] maps);
}
