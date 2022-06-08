package jpabook.jpashop.repository.orders.simplequery;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderSimpleQueryRepository {
    private EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.repository.orders.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
                        + " from Order o"
                        + " join o.member m"
                        + " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
