package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.repository.OrderJdbcRepository;
import kr.kade.batchinserttest.service.JdbcBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class JdbcBatchInsertServiceImpl implements JdbcBatchInsertService {
    private final OrderJdbcRepository orderRepository;

    @Override
    @ProcessTimeLogging
    public int batchInsert(List<OrderRequest> requests) {
        List<Order> orderEntities = requests.stream().map(OrderRequest::ofOrder).collect(Collectors.toList());
        return (int) StreamSupport.stream(orderRepository.saveAll(orderEntities).spliterator(), true).count();
    }
}
