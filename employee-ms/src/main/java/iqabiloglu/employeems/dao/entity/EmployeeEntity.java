package iqabiloglu.employeems.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String gender;

    @Column(name = "birth_date")
    LocalDate birthDate;

    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    String address;

    Double salary;

    @Column(name = "is_deleted")
    Boolean isDeleted;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    PositionEntity position;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return this.email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email);
    }
}
