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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Members member = new Members();
            member.setUsername("Test");
            member.setTeam(team);
            em.persist(member);

            // 아래 두개 써줘야 디비에서 값을 깔끔하게 가져옴
            em.flush();
            em.clear();

            Members findMember = em.find(Members.class, member.getId());
            List<Members> members = findMember.getTeam().getMembers();

            for(Members m : members) {
                System.out.println("m. = " + m.getUsername());
            }

            tx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        // code end
        emf.close();
    }
}
