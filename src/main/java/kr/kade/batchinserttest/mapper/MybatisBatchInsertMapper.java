package kr.kade.batchinserttest.mapper;

import kr.kade.batchinserttest.model.OrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisBatchInsertMapper {

    @Insert(value = "INSERT INTO table_order(order_id, product_nm, order_type)" +
            "VALUES(nextval('order_sequence'), #{product_nm}, #{order_type})")
    int batchInsert(OrderDto orderDto);
}
