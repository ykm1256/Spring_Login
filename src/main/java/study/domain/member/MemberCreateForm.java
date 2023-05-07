package study.domain.member;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    private String userId;

    private String password1;

    private String password2;

    private String email;

    private String name;
}
