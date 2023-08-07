package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 앱의 실제 동작에 필요한 '구현 객체를 생성'한다
@Configuration      // Spring에서 설정 정보
public class AppConfig {
    // @Bean을 method에 붙이면 Spring Container에 등록된다.
    @Bean
    public MemberService memberService() {                              // 이런 방식 = 생성자 주입
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderServiceImpl orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
/*
* 생각해보면 memberService, orderService 각각 1번씩 memberRepository 3번 호출될 것 같다
* 하지만 현실은 각 메서드는 한번씩만 호출된다 = spring은 singleton을 보장해준다*/

