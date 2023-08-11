package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*    @Autowired
    private DiscountPolicy rateDiscountPolicy;*/
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {       // 주문 생성 요청이 오면
        Member member = memberRepository.findById(memberId);                        // 회원 정보를 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 정책을 알기 위해 회원 정보를 넘김

        return new Order(memberId, itemName,itemPrice,discountPrice);               // 최종 생성된 주문을 반환
    }

}
