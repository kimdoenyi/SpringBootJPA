package jpabook.jpashop.repository.orders.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

// 엔티티가 아닌 쿼리로 찾는것
@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();

        result.forEach(o -> {
            List<OrderItemQueryDto> orderItems = findOrderItmes(o.getOrderId());
            o.setOrderItems(orderItems);
        });

        return result;
    }

    private List<OrderItemQueryDto> findOrderItmes(Long orderId) {
        return em.createQuery("select new jpabook.jpashop.repository.orders.query.OrderItemQueryDto(oi.order.id, i.name, oi.prderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i"+
                                " where oi.order.id = :orderId", OrderItemQueryDto.class
                                ).setParameter("orderId", orderId)
                                 .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
        return em.createQuery("select new jpabook.japshop.repository.orders.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o"+
                        " join o.member m"+
                        " join o.delivery d", OrderQueryDto.class).getResultList();
    }
}
