package ua.com.foxminded.hotelbooking.model.entity;

import com.google.common.base.MoreObjects.ToStringHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.com.foxminded.hotelbooking.model.entity.core.RoomCategory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.MoreObjects.toStringHelper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "additionalOptions"})
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column
    @Enumerated(value = EnumType.STRING)
    private RoomCategory category;

    @NotNull
    @Column
    private int number;

    @NotNull
    @Column(name = "price")
    private int pricePerNight;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdditionalOption> additionalOptions = new HashSet<>();

    @Override
    public String toString() {
        return formToStringHelper().toString();
    }

    private ToStringHelper formToStringHelper() {
        return toStringHelper(this)
                .add("id:", this.id)
                .add("category:", this.category)
                .add("number:", this.number)
                .add("pricePerNight:", this.pricePerNight)
                .add("additionalOptions:", this.additionalOptions)
                .omitNullValues();
    }
}
