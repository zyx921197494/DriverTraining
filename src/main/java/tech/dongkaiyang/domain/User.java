package tech.dongkaiyang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private int id;
    private String card;
    private String email;
    private String name;
    private String password;
    private int identity;
    private int rank;

}
