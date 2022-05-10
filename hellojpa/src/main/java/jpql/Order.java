package jpql;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
// Order 는 예약어라서 ORDERS 로 세팅해줘야함
public class Order {

    @Id @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
