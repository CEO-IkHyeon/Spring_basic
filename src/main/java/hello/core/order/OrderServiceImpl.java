package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();         // 1. member 찾아야됨
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();                // 2. 할인정보 알아야함
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();               // 3. 할인정보를 변경하려면 client측인 'OrderServiceImpl' 코드를 고쳐야함 --> 문제
    // 사실 의존 관계 분석해보면
    // 1. DiscountPolicy 와 RateDiscountPolicy 둘 다에 의존하고 있다 --> DIP 위반
    // 2. FixDiscountPolicy --> RateDiscountPolicy 변경하는 순간 OrderServiceImpl을 변경해야함 --> OCP 위반

    private DiscountPolicy discountPolicy;
    // 이렇게 실행하면 NullPointerException 발생하지만 DIP는 준수
    // 해결방안 : 누군가 client인 'OrderServiceImpl'에 'DiscountPolicy'의 구현 객체를 대신 생성해 주입하면 된다
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {       // 주문 생성 요청이 오면
        Member member = memberRepository.findById(memberId);                        // 회원 정보를 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 정책을 알기 위해 회원 정보를 넘김

        return new Order(memberId, itemName,itemPrice,discountPrice);               // 최종 생성된 주문을 반환
    }
}
