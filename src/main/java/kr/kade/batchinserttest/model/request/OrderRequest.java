package kr.kade.batchinserttest.model.request;

import kr.kade.batchinserttest.entity.Order;
import kr.kade.batchinserttest.model.OrderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {

    private String productNm;
    private String orderType;

    @Builder
    public OrderRequest(String productNm, String orderType) {
        this.orderType = orderType;
        this.productNm = productNm;
    }

    public static Order ofOrder(OrderRequest request) {
        return Order.builder()
                .orderType(request.getOrderType())
                .productNm(request.getProductNm())
                .build();
    }

    public static OrderDto ofOrderDto(OrderRequest request) {
        return OrderDto.builder()
                .orderType(request.getOrderType())
                .productNm(request.getProductNm())
                .build();
    }
}
