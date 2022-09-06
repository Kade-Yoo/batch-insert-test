package kr.kade.batchinserttest.service;

import kr.kade.batchinserttest.model.request.OrderRequest;

import java.util.List;

public interface MybatisBatchInsertService {

    int singleBatchSave(List<OrderRequest> requests);
    int multiBatchSave(List<OrderRequest> requests);
}
