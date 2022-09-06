package kr.kade.batchinserttest.service;

import kr.kade.batchinserttest.model.request.OrderRequest;

import java.util.List;

public interface JpaBatchInsertService {

    int batchInsert(List<OrderRequest> requests);
}
