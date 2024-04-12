package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setUserId(++sequence);

        store.put(member.getUserid(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                        .filter(member -> member.getName().equals(name))
                        .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void updateMember(Long id, Member updateParam) {

    }

    @Override
    public void deleteMember(Long memberId) {
        store.remove(memberId);
    }

    public void clearStore() {
        store.clear();
    }
}
