package shop.coding.mentor.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.coding.mentor.repositories.memberRepository.Member;
import shop.coding.mentor.repositories.memberRepository.MemberDao;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final MemberDao memberDao;

   public CustomUserDetailsService(MemberDao memberDao) {
      this.memberDao = memberDao;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String id) {
      return memberDao.findOneWithAuthoritiesById(id)
         .map(member -> createUser(id, member))
         .orElseThrow(() -> new UsernameNotFoundException(id + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   private org.springframework.security.core.userdetails.User createUser(String id, Member member) {
      if (!member.isActivated()) {
         throw new RuntimeException(id + " -> 활성화되어 있지 않습니다.");
      }

      List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
              .collect(Collectors.toList());

      return new org.springframework.security.core.userdetails.User(member.getId(),
              member.getPassword(),
              grantedAuthorities);
   }
}
