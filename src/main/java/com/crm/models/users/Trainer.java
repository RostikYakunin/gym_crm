package com.crm.models.users;

import com.crm.models.training.TrainingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "trainers")
@DynamicUpdate
public class Trainer extends User {
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "training_type_id", nullable = false)
    private TrainingType specialization;

    @Override
    public String toString() {
        return "Trainer{" +
                "user.id=" + user.getId() +
                ", firstname=" + user.getFirstName() +
                ", lastname=" + user.getLastName() +
                ", username=" + user.getUsername() +
                ", password=" + user.getPassword() +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
