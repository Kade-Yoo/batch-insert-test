package kr.kade.batchinserttest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class OrderDto {

    private long order_id;
    private String product_nm;
    private String order_type;

    @Builder
    public OrderDto(String product_nm, String order_type) {
        this.product_nm = product_nm;
        this.order_type = order_type;
    }
}
