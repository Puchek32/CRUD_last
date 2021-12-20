package testgroup.BOOT_prilozhenie.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public Role(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @JsonCreator
    public Role(@JsonProperty("name") String name) {
        this.name = name;
    }
}
