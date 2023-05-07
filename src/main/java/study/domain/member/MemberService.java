package study.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long mbrNo) {
        Optional<Member> member = memberRepository.findById(mbrNo);
        return member;
    }

    public void deleteById(Long mbrNo) {
        memberRepository.deleteById(mbrNo);
    }

    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Long id, Member member) {
        Optional<Member> e = memberRepository.findById(id);

        if (e.isPresent()) {
            e.get().setId(member.getId());
            e.get().setName(member.getName());
            memberRepository.save(member);
        }
    }

    public Member create(String userId, String email, String password, String name){
        Member member = new Member();
        member.setUserId(userId);
        member.setEmail(email);
        member.setName(name);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(password));
        this.memberRepository.save(member);
        return member;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        Member member = optionalMember.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(userId))
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        else
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        return new MemberDetail(member);
    }

}
