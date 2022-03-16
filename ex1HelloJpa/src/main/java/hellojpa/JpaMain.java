package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * JPA 에서는 트랜잭션 단위가 중요하다
 *
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   //  고객의 요청이 올때마다 생겼다가 버렸다가 생겼다가 버렸다

        EntityTransaction tx = em.getTransaction(); // db connection 을 얻는다고 생각하면 됨
        tx.begin();

        // code
        try {
            // 객체 생성 -> 비영속
            Members member = new Members();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속 -> 영속성 컨텍스트가 객체를 관리하기 시작함
            System.out.println("--------before-------");
            em.persist(member);
            System.out.println("-------after---------");
            em.detach(member);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        // code end
        emf.close();
    }
}
