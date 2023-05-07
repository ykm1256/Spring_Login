package study.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member save(Member member);
    public Optional<Member> findById(Long id);
    public Optional<Member> findByUserId(String userId);
    public List<Member> findAll();
}
