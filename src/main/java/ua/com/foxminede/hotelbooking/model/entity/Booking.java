package ua.com.foxminede.hotelbooking.model.entity;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "FK_rooms_bookings"))
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_users_bookings"))
    private User user;

    @Column
    private Period period;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id:", this.id)
                .add("room:", this.room)
                .add("user:", this.user)
                .add("period:", this.period)
                .omitNullValues()
                .toString();
    }
}
