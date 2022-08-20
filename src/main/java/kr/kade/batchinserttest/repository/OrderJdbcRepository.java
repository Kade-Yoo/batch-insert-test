package kr.kade.batchinserttest.repository;

import kr.kade.batchinserttest.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderJdbcRepository extends CrudRepository<Order, Long> {
}
