package shop.coding.mentor.repositories.LoginRepository;

import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

   @NotNull
   @Size(min = 3, max = 50)
   private String id;

   @NotNull
   @Size(min = 3, max = 100)
   private String password;
}
