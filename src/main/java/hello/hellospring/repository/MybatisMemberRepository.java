package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MybatisMemberRepository implements MemberRepository {

    @Autowired
    private final MemberMapper memberMapper;

    public MybatisMemberRepository(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member save(Member member) {
        memberMapper.save(member);

        return member;
    }

    @Override
    public Optional<Member> findbyId(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return memberMapper.findByName(name);

    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public void updateMember(Long id, Member updateParam) {
        memberMapper.updateMember(id, updateParam);
    }


    @Override
    public void deleteMember(Long memberId) {
        memberMapper.deleteMember(memberId);
    }
}
