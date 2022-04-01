package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // 양방향 설계
    @OneToMany(mappedBy = "team")
    private List<Members> members = new ArrayList<>(); // ArrayList 로 해야 add 할때 nullPointer 안남

    // 연관관계 편의 메서드
    public void addMember(Members member) {
        member.setTeam(this);
        members.add(member);
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

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }
}
