package shop.coding.mentor.repositories.memberRepository;

import lombok.*;
import jakarta.persistence.*;
import shop.coding.mentor.repositories.authorutyRepository.Authority;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

   @Id
   @Column(name = "member_key")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long memberKey;

   @Column(name = "id", nullable = false, length = 50, unique = true)
   private String id;

   @Column(name = "password", nullable = false, length = 100)
   private String password;

   @Column(name = "nickname", nullable = false, length = 20)
   private String nickname;

   @Column(name = "name", nullable = false, length = 20)
   private String name;

   @Column(name = "phone", nullable = false, length = 11)
   private String phone;

   @Column(name = "email", length = 40)
   private String email;

   @Column(name = "gender", length = 1)
   private String gender;

   @Column(name = "age")
   private Integer age;

   @Column(name = "memo")
   private String memo;

   @Column(name = "activated")
   private boolean activated;

   @ManyToMany
   @JoinTable(
      name = "member_authority",
      joinColumns = {@JoinColumn(name = "member_key", referencedColumnName = "member_key")},
      inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
   private Set<Authority> authorities;
}
