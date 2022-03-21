package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 기존에 만들어둔 멤버가 삭제가 안돼서 Members 로 생성함
 *
 */
@Entity
@Table(name = "MBR")    // MBR 테이블과 매핑됨, 테이블명 바꿀때 사용
public class Members {

    @Id // PK 가 뭔지 알려줘야함
    private Long id;
    private String name;

    // 동적 생성을 위한 기본 생성자 필수
    public Members() {
    }

    public Members(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
