package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    // @JsonIgnore -> 화면에 종속적
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    // 여기에 값을 세팅한다고해서 member 값이 변경되지않음
    private List<Order> orders = new ArrayList<>(); // 연관관계 거울 -> 읽기전용

}
