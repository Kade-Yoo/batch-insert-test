package kr.kade.batchinserttest.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "table_order")
@SequenceGenerator(name = "order_sequence_generator", sequenceName = "order_sequence", initialValue = 3, allocationSize = 1)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence_generator")
    private Long orderId;
    private String productNm;
    private String orderType;

    public Order() {}
    @Builder
    public Order(Long orderId, String productNm, String orderType) {
        this.orderId = orderId;
        this.productNm = productNm;
        this.orderType = orderType;
    }
}
