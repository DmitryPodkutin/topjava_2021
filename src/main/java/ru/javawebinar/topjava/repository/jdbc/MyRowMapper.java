package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Objects;

public class MyRowMapper implements RowMapper<User> {
    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    @Override
    public User mapRow(@NonNull ResultSet resultSet, int rowNum) throws SQLException {
        User user = ROW_MAPPER.mapRow(resultSet, rowNum);
        Objects.requireNonNull(user).setRoles(EnumSet.noneOf(Role.class)); //  https://coderoad.ru/41117898/Как-создать-пустой-EnumSet
        do {
            user.getRoles().add(Role.valueOf(resultSet.getString("role")));
        } while (resultSet.next());
        return user;
    }
}
