package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MssqlMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MemberController {

    private final MssqlMemberService memberService;

    @Autowired
    public MemberController(MssqlMemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입
    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원가입
    @PostMapping("/members/new")
    public String create(MemberForm form, RedirectAttributes redirect) {
        Member member = new Member();

        System.out.println(form.getUserid());
        member.setUserId(form.getUserid());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setAge(form.getAge());

        Long join = memberService.join(member);
        if (join != -1 ) {
            redirect.addAttribute("msg", "회원가입이 완료되었습니다");
            System.out.println(join);
        }
        else {
            redirect.addAttribute("msg", "중복된 이름이 존재합니다.");
            System.out.println(join);
        }

        return "redirect:/members/resultJoinForm";
    }

//    // 회원가입
//    @PostMapping("/members/new/{id}")
//    public String create(@PathVariable Long id, RedirectAttributes redirect) {
//        Member member = new Member();
//
//        member.setName((String) param.get("username"));
//        member.setPassword((String) param.get("password"));
//        member.setAge((Long) param.get("age"));
//
//        Long join = memberService.join(member);
//        if (join != -1 ) {
//            redirect.addAttribute("msg", "회원가입이 완료되었습니다");
//            System.out.println(join);
//        }
//        else {
//            redirect.addAttribute("msg", "중복된 이름이 존재합니다.");
//            System.out.println(join);
//        }
//
//        return "redirect:/members/resultJoinForm";
//    }

    @GetMapping(value = "/members/resultJoinForm")
    public String result_alarm(@RequestParam("msg") String param, Model model)
    {
        model.addAttribute("msg", param);
        return "members/resultJoinForm";
    }

    // 회원 목록
    @GetMapping("/members")
    public String list(Model model, RedirectAttributes redirect) throws Exception {

        List<Member> members = memberService.findMembers();

        if (members.isEmpty()) {
            redirect.addAttribute("msg", "회원이 존재하지 않습니다.");
            return "redirect:/members/resultJoinForm";
        }
        else {
            model.addAttribute("members", members);
            return "members/memberList";
        }
    }

    // 회원 검색
    @GetMapping("members/memberFind")
    public String createMemberFindForm() {
        return "members/findMemberForm";
    }

    // 회원 검색
    @PostMapping("members/memberFind")
    public String list2(RedirectAttributes redirect, Model model, MemberForm form) throws Exception {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println(member.getName());
        if (member.getName() == "") {
            return "redirect:/members/memberFind";
        }

        Optional<Member> memberOne = memberService.findByName(member.getName());
        System.out.println(memberOne.get().getUserid());

        if (memberOne.isPresent()) {
            model.addAttribute("memberOne", memberOne.get());
            return "members/memberOne";
        }
        else {
            redirect.addAttribute("msg", "회원이 존재하지 않습니다.");
            return "redirect:/members/resultJoinForm";
        }

//        if (memberOne != null) {
//            model.addAttribute("memberOne", memberOne);
//            return "members/memberOne";
//        }
//        else {
//            redirect.addAttribute("msg", "회원이 존재하지 않습니다.");
//            return "/members/resultJoinForm";
//        }

    }

    @PostMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);

        return "redirect:/members";
    }

//    @PostMapping("/updateMember/{id}")
//    public String updateMember(@PathVariable Long id) {
//        Optional<Member> member = memberService.findOne(id);
//
//        memberService.updateMember(id, member.get());
//
//        return "redirect:/members";
//    }
//
    @PostMapping("/updateMember/{id}")
    public String updateMember(@PathVariable Long id, Model model) {
        Optional<Member> member = memberService.findOne(id);
        System.out.println(member.get().getUserid());

        model.addAttribute("memberOne", member.get());

        return "members/updateMemberForm";
//        return "/home";
    }

    @PostMapping("members/memberUpdate/{id}")
    public String updateMemberList(@PathVariable Long id,Model model, MemberForm form) throws Exception {
        Member newMember = new Member();
        newMember.setUserId(id);

        System.out.println(form.getName());
        System.out.println(form.getAge());

        newMember.setPassword(form.getPassword());
        newMember.setName(form.getName());
        newMember.setAge(form.getAge());

        memberService.updateMember(id, newMember);

        return "/home";
    }
}
