INSERT INTO users
    (user_id, username, password, is_admin)
SELECT uuid_generate_v4(), 'admin', 'password', TRUE
WHERE
    NOT EXISTS (
        SELECT username FROM users WHERE username = 'admin'
    );

INSERT INTO users
    (user_id, username, password, is_admin)
SELECT uuid_generate_v4(), 'basic_user', 'password', FALSE
WHERE
    NOT EXISTS (
        SELECT username FROM users WHERE username = 'basic_user'
    );