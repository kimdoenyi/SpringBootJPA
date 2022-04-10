package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 기존에 만들어둔 멤버가 삭제가 안돼서 Members 로 생성함
 *
 */
@Entity
public class Members extends BaseEntity{

    @Id @GeneratedValue
    private Long id;    // pk 매핑

    @Column(name = "USERNAME")
    private String username;

    // 다대일 단방향
    @ManyToOne  // 멤버 기준에서 다대일 - 멤버 N : 팀 1
    @JoinColumn(name = "TEAM_ID")   // 외래키로 연관관계있으니까 매핑 필수 !
    private Team team;


    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member") // mappedBy 대소문자 가림 ,, 충격
    private List<MemberProduct> memberProduct = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
