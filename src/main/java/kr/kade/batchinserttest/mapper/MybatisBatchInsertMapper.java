package kr.kade.batchinserttest.mapper;

import kr.kade.batchinserttest.model.OrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MybatisBatchInsertMapper {

    @Insert(value = "INSERT INTO table_order(order_id, product_nm, order_type)" +
            "VALUES(nextval('order_sequence'), #{productNm}, #{orderType})")
    int singleInsert(OrderDto orderDto);

    int batchInsert(@Param("list") List<OrderDto> list);
}
