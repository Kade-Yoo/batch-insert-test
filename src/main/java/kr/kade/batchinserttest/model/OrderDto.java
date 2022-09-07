package kr.kade.batchinserttest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class OrderDto {

    private long orderId;
    private String productNm;
    private String orderType;

    @Builder
    public OrderDto(String productNm, String orderType) {
        this.productNm = productNm;
        this.orderType = orderType;
    }
}
