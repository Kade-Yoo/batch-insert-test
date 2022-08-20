package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.repository.OrderJdbcRepository;
import kr.kade.batchinserttest.service.JdbcBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcBatchInsertServiceImpl implements JdbcBatchInsertService {
    private final OrderJdbcRepository orderRepository;

    @Override
    @ProcessTimeLogging
    public Iterable<Order> batchInsert() {
        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < 10000; i++) {
            orders.add(Order.builder().orderType("COMPLETE").productNm("Product" + i + 3).build());
        }
        return orderRepository.saveAll(orders);
    }
}
