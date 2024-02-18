package ru.gb.tasks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "performer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank()
    private String password;

    @ManyToMany(mappedBy = "performers")
    private List<Task> tasks;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role",
            joinColumns = @JoinColumn(name = "performer_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"performer_id", "role"}, name = "uc_user_role"))
    @Column(name = "role")
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Set<Role> roles;

    @OneToOne(mappedBy = "performer")
    private Session session;
}
