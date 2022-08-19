package kr.kade.batchinserttest.service;

import kr.kade.batchinserttest.entity.Order;

import java.util.List;

public interface JpaBatchInsertService {

    List<Order> batchInsert();
}
