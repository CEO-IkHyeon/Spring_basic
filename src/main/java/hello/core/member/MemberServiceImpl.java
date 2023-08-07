package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 이 코드는 MemberRepository에도 의존하고 MemoryMemberRepository에도 의존 -> DIP 위반
    private final MemberRepository memberRepository;
    // 생성자를 통해 'MemberServiceImpl'에서 'MemoryMemberRepository' 코드를 없앰 -> MemberRepository(interface)에만 의존 = DIP 의존
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
