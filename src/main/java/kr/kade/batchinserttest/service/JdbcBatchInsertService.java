package kr.kade.batchinserttest.service;

import kr.kade.batchinserttest.model.request.OrderRequest;

import java.util.List;

public interface JdbcBatchInsertService {

    int batchInsert(List<OrderRequest> requests);
}
