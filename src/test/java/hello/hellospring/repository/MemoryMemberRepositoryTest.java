package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 아래의 각 메서드가 끝날때마다 매번 실행되는 메서드
    // MemoryMemberRepository를 clear하지 않으면 아래의 메서드를 한꺼번에 테스트 진행할때 에러가 발생한다
    // 회원가입이 여러번 되기 때문이다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void saveTest() {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 데이터 검증용 메서드
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByNameTest() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAllTest() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }



}
