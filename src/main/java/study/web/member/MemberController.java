package study.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.domain.member.Member;
import study.domain.member.MemberCreateForm;
import study.domain.member.MemberDetail;
import study.domain.member.MemberService;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal MemberDetail principal, Model model) {
        model.addAttribute("name", principal.getUsername());
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!memberCreateForm.getPassword1().equals(memberCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup";
        }

        memberService.create(memberCreateForm.getUserId(), memberCreateForm.getEmail(), memberCreateForm.getPassword1(), memberCreateForm.getName());

        return "redirect:/login";
    }

}
