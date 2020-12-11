insert into user_table (
    user_id,
    user_username,
    user_password
)values (
    nextval('user_seq'),
    'admin',
    '$2a$10$uXzoc5GccigVSmFpGV.FWuWGahO5KzBOn1g6VcTRUSf8vkG9dy/Fy'
);
