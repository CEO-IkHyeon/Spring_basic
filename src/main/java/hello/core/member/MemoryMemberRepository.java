package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();               // Member 객체를 저장하고 검색하는 메모리 기반 저장소 역할 -> 실제 DB 대신 테스트용으로 활용
    @Override
    public void save(Member member) {                                       // member를 'store'라는 Map에 저장함 -> key : Member 객체의 id / value : Member 객체 자체를 반환
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }   // memberId를 넘기면 'store'에서 key(id)에 해당하는 value(member)를 리턴
}
