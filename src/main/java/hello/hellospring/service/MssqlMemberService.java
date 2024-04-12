package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MssqlMemberService {

    private final MemberRepository memberRepository;

    public MssqlMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */

    public Long join(Member member) {
//        validateDuplicateMember(member);

        Member newMember = memberRepository.save(member);
        return newMember.getUserid();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("회원이 이미 존재합니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByName(String name){
        return memberRepository.findByName(name);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }
    public void updateMember(Long id, Member member) {
        memberRepository.updateMember(id, member);
    }

}
