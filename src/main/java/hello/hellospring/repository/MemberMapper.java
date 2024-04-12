package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    Long save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void updateMember(@Param("id") Long id, @Param("updateParam") Member member);

    void deleteMember(Long memberId);
}