package org.example.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Mapper
public abstract class UserMapper {

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserToAuth {

        @NotNull
        @Size(min = 4, max = 40)
        String username;

        @NotNull
        @Size(min = 4, max = 40)
        String password;
    }

    public abstract User mapToAuth(UserToAuth userToSave);

    public abstract UserToAuth mapToAuth(User user);

}
