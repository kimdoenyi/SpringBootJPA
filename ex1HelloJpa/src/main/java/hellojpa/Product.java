package hellojpa;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

}
