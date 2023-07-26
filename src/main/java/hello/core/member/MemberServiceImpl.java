package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // ctrl + shift + enter = 자동완성 + 세미콜론까지
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
