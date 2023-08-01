package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();        // memberService 에 MemberServiceImpl 주입

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);    // 'applicationContext'가 AppConfig에 있는 객체들을 생성해서 관리해준다
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// name에는 메서드이름을 넣는다. 두 번째는 타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
