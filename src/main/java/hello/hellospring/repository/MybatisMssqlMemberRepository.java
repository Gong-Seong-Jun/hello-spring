package hello.hellospring.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import hello.hellospring.domain.Member;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Repository
public class MybatisMssqlMemberRepository implements MemberRepository {

    @Autowired
    private final MssqlMemberMapper memberMapper;

    public MybatisMssqlMemberRepository(MssqlMemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member save(Member member) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        json = "{";
        json += "\"USERID\":\"" + member.getUserid().toString() + "\",";
        json += "\"NAME\":\"" + member.getName() + "\",";
        json += "\"PASSWORD\":\"" + member.getPassword() + "\",";
        json += "\"AGE\":\"" + member.getAge().toString() + "\"";
        json += "}";

        Map<String, Object> param = new HashMap<>();
        param.put("@pv_json_i", json);
        param.put("@pv_json_o", "");
        param.put("@pv_retcod_o", "");
        param.put("@pv_retmsg_o", "");

        System.out.println(param);
        System.out.println(json);
//        memberMapper.save(member);
        memberMapper.saveMember(param);
        System.out.println(param);
//        Optional<Member> savedMember = memberMapper.findByName(member.getName());

        return member;
//        return savedMember.get();
    }

    @Override
    public void updateMember(Long id, Member updateParam) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        json = "{";
        json += "\"USERID\":\"" + updateParam.getUserid().toString() + "\",";
        json += "\"NAME\":\"" + updateParam.getName() + "\",";
        json += "\"PASSWORD\":\"" + updateParam.getPassword() + "\",";
        json += "\"AGE\":\"" + updateParam.getAge().toString() + "\"";
        json += "}";

        Map<String, Object> param = new HashMap<>();
        param.put("@pv_json_i", json);
        param.put("@pv_json_o", "");
        param.put("@pv_retcod_o", "");
        param.put("@pv_retmsg_o", "");

        System.out.println(param);
        System.out.println(json);

        memberMapper.updateMember(param);
    }

    @Override
    public void deleteMember(Long memberId) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;


        json = "{";
        json += "\"USERID\":\"" + memberId.toString() + "\"";
        json += "}";

        Map<String, Object> param = new HashMap<>();
        param.put("@pv_json_i", json);
        param.put("@pv_json_o", "");
        param.put("@pv_retcod_o", "");
        param.put("@pv_retmsg_o", "");

        System.out.println(param);
        System.out.println(json);

        memberMapper.deleteMember(param);
    }

    @Override
    public Optional<Member> findById(Long id) {
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

}
