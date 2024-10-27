package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    private String id;

    private String name;
}
