package hellojpa;

import javax.persistence.*;
import java.util.*;

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

    // 기간
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name ="city", column=@Column(name = "WORK_CITY")),
                        @AttributeOverride(name ="street", column=@Column(name = "WORK_STREET")),
                        @AttributeOverride(name ="zipCode", column=@Column(name = "WORK_ZIP_CODE")),
    })
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private List<Address> addressHistory = new ArrayList<>();

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

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<MemberProduct> getMemberProduct() {
        return memberProduct;
    }

    public void setMemberProduct(List<MemberProduct> memberProduct) {
        this.memberProduct = memberProduct;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
}
