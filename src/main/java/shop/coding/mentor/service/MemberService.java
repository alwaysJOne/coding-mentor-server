package shop.coding.mentor.service;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.coding.mentor.exception.DuplicateMemberException;
import shop.coding.mentor.exception.NotFoundMemberException;
import shop.coding.mentor.repositories.authorutyRepository.Authority;
import shop.coding.mentor.repositories.memberRepository.Member;
import shop.coding.mentor.repositories.memberRepository.MemberDao;
import shop.coding.mentor.repositories.memberRepository.MemberDto;
import shop.coding.mentor.utils.SecurityUtil;

@Service
public class MemberService {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberDao memberDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberDao.findOneWithAuthoritiesById(memberDto.getId()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member user = Member.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .phone(memberDto.getPhone())
                .email(memberDto.getEmail())
                .age(memberDto.getAge())
                .memo(memberDto.getMemo())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberDao.save(user));
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String id) {
        return MemberDto.from(memberDao.findOneWithAuthoritiesById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberDao::findOneWithAuthoritiesById)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}
