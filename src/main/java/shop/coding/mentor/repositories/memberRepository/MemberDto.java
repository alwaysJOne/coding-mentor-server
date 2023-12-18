package shop.coding.mentor.repositories.memberRepository;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import shop.coding.mentor.repositories.authorutyRepository.AuthorityDto;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

   @NotNull
   @Size(min = 3, max = 50)
   private String id;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @NotNull
   @Size(min = 3, max = 100)
   private String password;

   @NotNull
   @Size(min = 3, max = 20)
   private String nickname;

   @NotNull
   @Size(min = 3, max = 20)
   private String name;

   @NotNull
   @Size(min = 3, max = 11)
   private String phone;

   private String email;
   private Integer age;
   private String memo;

   private Set<AuthorityDto> authorityDtoSet;

   public static MemberDto from(Member member) {
      if(member == null) return null;

      return MemberDto.builder()
              .id(member.getId())
              .nickname(member.getNickname())
              .name(member.getName())
              .phone(member.getPhone())
              .email(member.getEmail())
              .age(member.getAge())
              .memo(member.getMemo())
              .authorityDtoSet(member.getAuthorities().stream()
                      .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                      .collect(Collectors.toSet()))
              .build();
   }
}