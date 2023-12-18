package shop.coding.mentor.repositories.memberRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDao extends JpaRepository<Member, Long> {
   @EntityGraph(attributePaths = "authorities")
   Optional<Member> findOneWithAuthoritiesById(String id);
}
