package hello.hellospring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// JPA가 관리하는 객체로 인식
@Entity
public class Member {

    // PK로 관리
    @Id
    // MySQL의 AUTO_INCREMENT 같은 기능을 IDENTITY 전략이라고 한다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 실제 DB 테이블의 컬럼명을 지정
//  @Column(name = "name")
    private String name;

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
