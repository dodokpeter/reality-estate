package com.example.demo.outputs.adapters.entities;

import com.example.demo.entities.Media;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UsersTable")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Override
    public String toString() {
        return "Reality{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", price=" + firstName +
                ", rooms=" + lastName +
                ", area=" + email +
                ", description='" + phoneNumber + '\'' +
                '}';
    }
}
