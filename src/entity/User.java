package entity;

import lombok.*;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    BigInteger id;
    String name;
    String email;
}
