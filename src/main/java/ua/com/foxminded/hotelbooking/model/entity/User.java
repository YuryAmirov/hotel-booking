package ua.com.foxminded.hotelbooking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.com.foxminded.hotelbooking.model.entity.validator.annotation.ValidEmail;
import ua.com.foxminded.hotelbooking.model.entity.validator.annotation.ValidPassword;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, message = "First name shouldn't be shorter than 2 symbols!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name shouldn't be shorter than 2 symbols!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @ValidEmail
    @Column
    private String email;

    @NotNull
    @ValidPassword(lengthMin =5,
            lengthMax = 100,
            uppercaseCount = 2,
            lowercaseCount = 2,
            specialCharacter = 1,
            message = "Password must contain at least 2 upper case symbols, 2 lower case symbols and 1 special symbol!")
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
