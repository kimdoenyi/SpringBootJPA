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
            // 데이터 변경시
            //Members members = em.find(Members.class, 150L);
            //members.setName("Update New Name");

            System.out.println(" ============================ " );

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
