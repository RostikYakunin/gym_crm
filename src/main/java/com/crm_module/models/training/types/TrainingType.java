package com.crm_module.models.training.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum TrainingType {
    FITNESS(1, "Fitness"),
    YOGA(2, "Yoga"),
    ZUMBA(3, "Zumba"),
    STRETCHING(4, "Stretching"),
    RESISTANCE(5, "Resistance");

    private final long id;
    private final String name;

    public static TrainingType getById(long id) {
        for (TrainingType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown id: " + id);
    }
}
