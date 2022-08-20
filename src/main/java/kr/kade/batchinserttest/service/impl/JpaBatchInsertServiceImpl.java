package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.repository.OrderRepository;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaBatchInsertServiceImpl implements JpaBatchInsertService {

    private final OrderRepository orderRepository;

    @Override
    @ProcessTimeLogging
    public List<Order> batchInsert() {
        List<Order> orders = new ArrayList<>();
        for (long i = 0; i < 10000; i++) {
            orders.add(Order.builder().orderType("COMPLETE").productNm("Product" + i + 3).build());
        }
        return orderRepository.saveAll(orders);
    }
}
