package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();         // 1. member 찾아야됨
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();                // 2. 할인정보 알아야함
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();               // 3. 할인정보를 변경하려면 client측인 'OrderServiceImpl' 코드를 고쳐야함 --> 문제
    // 사실 의존 관계 분석해보면
    // 1. DiscountPolicy 와 RateDiscountPolicy 둘 다에 의존하고 있다 --> DIP 위반
    // 2. FixDiscountPolicy --> RateDiscountPolicy 변경하는 순간 OrderServiceImpl을 변경해야함 --> OCP 위반

     /*
     * 문제점 정리 : A interface의 구현체에서 B interface의 구현체를 정의하는 것이 문제 -> 다양한 책임을 갖게 만듦
     ( = 로미오 역할(OrderService)을 하는 디카프리오(OrderServiceImpl)가 줄리엣 역할(DiscountPolicy)을 하는 여주(FixDiscountPolicy)를 직접 고르는 것과 같음)
     *
     * 해결책 : AppConfig -> 앱의 전체 동작 방식을 구성, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들자
     * (= 공연 구성 및 담당 배우 섭외하고, 역할에 맞는 배우 지정하는 책임을 담당하는 별도의 "공연 기획자"가 필요!!!)
     * */


    // 결론 : 아래와 같이 하면 DIP를 준수 -> 오직 interface에만 의존!!!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 이렇게 실행하면 NullPointerException 발생하지만 DIP는 준수
    // 해결방안 : 누군가 client인 'OrderServiceImpl'에 'DiscountPolicy'의 구현 객체를 대신 생성해 주입하면 된다

    //
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;   // 'MemoryMemberRepository' 주입
        this.discountPolicy = discountPolicy;       // 'FixDiscountPolicy' 주입
    }
    // 결론 : 'OrderServiceImpl'에는 'MemoryRepository'와 'FixDiscountPolicy' 객체의 의존관계 주입

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {       // 주문 생성 요청이 오면
        Member member = memberRepository.findById(memberId);                        // 회원 정보를 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 정책을 알기 위해 회원 정보를 넘김

        return new Order(memberId, itemName,itemPrice,discountPrice);               // 최종 생성된 주문을 반환
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
