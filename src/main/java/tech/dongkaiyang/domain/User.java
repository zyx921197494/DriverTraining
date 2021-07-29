package tech.dongkaiyang.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private int id;
    private String card;
    private String email;
    private String name;
    private String password;
    private int identity;
    private int rank;

}
