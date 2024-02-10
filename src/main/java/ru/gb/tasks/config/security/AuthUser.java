package ru.gb.tasks.config.security;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;
import ru.gb.tasks.model.Performer;

@Getter
public class AuthUser extends User {
    private final Performer performer;

    public AuthUser(@NotNull Performer performer) {
        super(performer.getName(), "{noop}" + performer.getPassword(), performer.getRoles());
        this.performer = performer;
    }

    public String getId() {
        return performer.getId().toString();
    }
}
