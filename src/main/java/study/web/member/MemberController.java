package study.web.member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.domain.member.Member;
import study.domain.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Member>> getAllMembers() {
        System.out.println(memberService);
        List<Member> member = memberService.findAll();
        return new ResponseEntity<List<Member>>(member, HttpStatus.OK);
    }

    @DeleteMapping(value="/{loginId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteMember(@PathVariable("loginId") Long loginId){
        memberService.deleteById(loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 회원 입력
    @PostMapping
    public ResponseEntity<Member> save(Member member) {
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 입력
    @RequestMapping(value="/saveMember", method = RequestMethod.GET)
    public ResponseEntity<Member> save(HttpServletRequest req, Member member){
        return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
    }

}
