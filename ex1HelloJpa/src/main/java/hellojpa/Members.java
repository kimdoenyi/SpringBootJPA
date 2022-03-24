package hellojpa;

import javax.persistence.*;
import java.util.Date;

/**
 * 기존에 만들어둔 멤버가 삭제가 안돼서 Members 로 생성함
 *
 */
@Entity
public class Members {

    @Id
    private Long id;    // pk 매핑

    @Column(name = "name")  // column명은 name
    private String username;    // 객체는 username

    private Integer age;

    @Enumerated(EnumType.STRING)    // DB 에는 enum 타입이 없음, 이거 써주면 됨
    private RoleType roleType;

    // DB 는 자바랑 다르게 날짜를 3가지로 구분하기때문에 선언해줘야함
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob    // 큰 컨텐츠를 넣고싶을때
    private String description;

    public Members() {}


}
