package study.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import study.domain.login.LoginService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

}
