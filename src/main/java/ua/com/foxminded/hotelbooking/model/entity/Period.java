package ua.com.foxminded.hotelbooking.model.entity;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Period {

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Start Date:", this.startDate)
                .add("End Date:", this.endDate)
                .omitNullValues()
                .toString();
    }
}
