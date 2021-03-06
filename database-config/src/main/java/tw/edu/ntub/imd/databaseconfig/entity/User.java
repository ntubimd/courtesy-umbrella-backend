package tw.edu.ntub.imd.databaseconfig.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import tw.edu.ntub.imd.databaseconfig.Config;
import tw.edu.ntub.imd.databaseconfig.converter.BooleanTo1And0Converter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user", schema = Config.DATABASE_NAME)
@Data
@EqualsAndHashCode(exclude = {
        "userRoleByRoleId"
})
public class User {
    @Id
    @Column(name = "account", length = 100, nullable = false)
    private String account;
    @Column(name = "password", length = 60, nullable = false)
    private String password;
    @Column(name = "role_id", nullable = false)
    private Integer roleId = 3;
    @Column(name = "enable", nullable = false)
    @Convert(converter = BooleanTo1And0Converter.class)
    private Boolean enable = true;
    @Column(name = "email", length = 255, nullable = false)
    private String email;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();
    @Column(name = "modify_date", nullable = false)
    private LocalDateTime modifyDate = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UserRole userRoleByRoleId;

    public void setUserRoleByRoleId(UserRole userRoleByRoleId) {
        this.userRoleByRoleId = userRoleByRoleId;
        if (userRoleByRoleId != null) {
            setRoleId(userRoleByRoleId.getId());
        }
    }
}
