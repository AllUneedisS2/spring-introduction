package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//
@Transactional
public class JpaMemberRepository implements MemberRepository {

    // Entity를 데이터베이스와 연결하여 관리하는 인터페이스
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK 기반의 쿼리가 아니라면 jpql을 작성해야함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    // PK 기반의 쿼리가 아니라면 jpql을 작성해야함
    @Override
    public List<Member> findAll() {
        // jpql이라는 쿼리 언어이다
        // from Member (as) m 에서 Member는 Entity에 등록된 객체 클래스를 가르키는 것이다
        // select m 에서 m은 from Member (as) m 의 m을 가르키는 것이고 select * 과 같다
        // createQuery()로 쿼리 객체를 만들고 getResultList()로 쿼리를 실행하고 결과값을 가져온다
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
