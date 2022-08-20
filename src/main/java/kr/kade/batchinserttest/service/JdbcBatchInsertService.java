package kr.kade.batchinserttest.service;

import kr.kade.batchinserttest.entity.Order;

public interface JdbcBatchInsertService {

    Iterable<Order> batchInsert();
}
