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
            // JPQL
            // 대상이 테이블이 아니라 객체임, Members > 테이블 X , 객체 O
            List<Members> result = em.createQuery("select m from Members as m")
                                    .setFirstResult(0)  // 페이징 , 0부터 시작임
                                    .setMaxResults(10)  // 페이징
                                    .getResultList();

            for(Members member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
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
