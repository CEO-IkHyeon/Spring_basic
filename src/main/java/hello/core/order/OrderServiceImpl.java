package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();     // 1. member 찾아야됨
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();              // 2. 할인정보 알아야함


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {       // 주문 생성 요청이 오면
        Member member = memberRepository.findById(memberId);                        // 회원 정보를 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 정책을 알기 위해 회원 정보를 넘김

        return new Order(memberId, itemName,itemPrice,discountPrice);               // 최종 생성된 주문을 반환
    }
}
