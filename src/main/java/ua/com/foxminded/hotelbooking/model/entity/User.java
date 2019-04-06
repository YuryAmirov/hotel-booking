package ua.com.foxminded.hotelbooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static com.google.common.base.MoreObjects.ToStringHelper;
import static com.google.common.base.MoreObjects.toStringHelper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String password;

    @Override
    public String toString() {
        return formToStringHelper().toString();
    }

    private ToStringHelper formToStringHelper() {
        return toStringHelper(this)
                .add("id:", this.id)
                .add("firstName:", this.firstName)
                .add("lastName:", this.lastName)
                .add("email:", this.email)
                .add("password:", this.password)
                .omitNullValues();
    }
}
