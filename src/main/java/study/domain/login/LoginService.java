package study.domain.login;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.domain.member.Member;
import study.domain.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginService {

    private MemberRepository memberRepository;

    public Member login(String userId, String password){
        return memberRepository.findByUserId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
