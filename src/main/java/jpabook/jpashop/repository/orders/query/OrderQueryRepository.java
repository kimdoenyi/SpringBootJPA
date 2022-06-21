package jpabook.jpashop.repository.orders.query;

import jpabook.jpashop.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return em.createQuery("select new jpabook.jpashop.repository.orders.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i"+
                                " where oi.order.id = :orderId", OrderItemQueryDto.class
                                ).setParameter("orderId", orderId)
                                 .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
        return em.createQuery("select new jpabook.jpashop.repository.orders.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o"+
                        " join o.member m"+
                        " join o.delivery d", OrderQueryDto.class).getResultList();
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        List<Long> orderIds = toOrderIds(result);
        // 쿼리는 한번 날리고
        // 메모리에서 값을 매칭해줌 . 메모리에 맵을 올려두는게 핵심
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItems(orderIds).stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        // 쿼리를 두번으로 최적화를 할 수 있음

        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery(
                    "select new jpabook.jpashop.repository.orders.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " from OrderItem oi" +
                        " join oi.item i"+
                        " where oi.order.id in :orderIds", OrderItemQueryDto.class
                ).setParameter("orderIds", orderIds)
                .getResultList();
        return orderItems;
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result) {
        return result.stream()
                .map(o -> o.getOrderId())
                .collect(Collectors.toList());
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery("select new "+
                                " jpabook.jpashop.repository.orders.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)" +
                                " from Order o"+
                                " join o.member m" +
                                " join o.delivery d" +
                                " join o.orderItems oi"+
                                " join oi.item i", OrderFlatDto.class
                ).getResultList();
    }
}
