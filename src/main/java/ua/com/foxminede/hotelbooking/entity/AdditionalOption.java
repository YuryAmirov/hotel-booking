package ua.com.foxminede.hotelbooking.entity;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.com.foxminede.hotelbooking.entity.core.AdditionalOptionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static com.google.common.base.MoreObjects.toStringHelper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity
@Table(name = "additional_options")
public class AdditionalOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

    @NotNull
    @Column
    @Enumerated(value = EnumType.STRING)
    private AdditionalOptionType type;

    @NotNull
    @Column(name = "price")
    private int pricePerNight;

    @Override
    public String toString() {
        return formToStringHelper().toString();
    }

    private MoreObjects.ToStringHelper formToStringHelper() {
        return toStringHelper(this)
                .add("id:", this.id)
                .add("room:", this.room)
                .add("type:", this.type)
                .add("pricePerNight:", this.pricePerNight)
                .omitNullValues();
    }
}
