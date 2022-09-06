package kr.kade.batchinserttest.service.impl;

import kr.kade.batchinserttest.annotation.ProcessTimeLogging;
import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.model.request.OrderRequest;
import kr.kade.batchinserttest.repository.OrderRepository;
import kr.kade.batchinserttest.service.JpaBatchInsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaBatchInsertServiceImpl implements JpaBatchInsertService {

    private final OrderRepository orderRepository;

    @Override
    @ProcessTimeLogging
    public int batchInsert(List<OrderRequest> requests) {
        List<Order> orderEntities = requests.stream().map(OrderRequest::ofOrder).collect(Collectors.toList());
        return orderRepository.saveAll(orderEntities).size();
    }
}
