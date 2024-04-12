package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MssqlMemberMapper {

//    void updateMember(@Param("param") Map<String, Object> param);

    // 아이디로 조회
    Optional<Member> findById(Long id);

    // 이름으로 조회
    Optional<Member> findByName(String name);

    // 전체 조회
    List<Member> findAll();

    // 회원가입 -> 프로시저 대체
    void saveMember(Map<String, Object> param );

    // 수정 -> 프로시저 대체
    void updateMember(Map<String, Object> param);

    // 삭제 -> 프로시저 대체
    void deleteMember(Map<String, Object> param);

//    // 회원가입 -> 프로시저 대체
//    Long save(Member member);
//
//    // 수정 -> 프로시저 대체
//    void updateMember(@Param("id") Long id, @Param("updateParam") Member member);
//
//    // 삭제 -> 프로시저 대체
//    void deleteMember(Long memberId);

}