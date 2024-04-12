package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

      // H2 mapper
//    private final MemberMapper memberMapper;
//
//    @Autowired
//    public SpringConfig(MemberMapper memberMapper) {
//        this.memberMapper = memberMapper;
//    }

    // mssql mapper
    private final MssqlMemberMapper memberMapper;

    @Autowired
    public SpringConfig(MssqlMemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
        return new MybatisMssqlMemberRepository(memberMapper);
    }
}
