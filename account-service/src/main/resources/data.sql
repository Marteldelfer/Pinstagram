CREATE TABLE IF NOT EXISTS account
(
    id                  UUID PRIMARY KEY,
    name                VARCHAR(255)        NOT NULL,
    username            VARCHAR(255)        NOT NULL,
    email               VARCHAR(255) UNIQUE NOT NULL,
    bio                 VARCHAR(255),
    deleted             BOOLEAN             NOT NULL,
    is_private          BOOLEAN             NOT NULL,
    validated           BOOLEAN             NOT NULL,
    created_at          TIMESTAMP           NOT NULL,
    publication_count   INTEGER             NOT NULL,
    follower_count      INTEGER             NOT NULL,
    following_count     INTEGER             NOT NULL,
    avatar_url          VARCHAR(255)
);

INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174000',
        'John Roboto',
        'johnroboto',
        'john.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174000');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174001',
        'Joseph Roboto',
        'josephroboto',
        'joseph.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174001');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174002',
        'Matias Roboto',
        'matiasroboto',
        'matias.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174002');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174003',
        'Debora Roboto',
        'deboraroboto',
        'debora.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174003');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174004',
        'Johan Roboto',
        'johanroboto',
        'johan.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174004');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174005',
        'Jessica Roboto',
        'jessicaroboto',
        'jessica.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174005');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174006',
        'Maria Roboto',
        'mariaroboto',
        'maria.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174006');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174007',
        'Andre Roboto',
        'andreroboto',
        'andre.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174007');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174008',
        'Gabriella Roboto',
        'gabriellaroboto',
        'gabriella.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174008');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174009',
        'Paulo Roboto',
        'pauloroboto',
        'paulo.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174009');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174010',
        'Emma Roboto',
        'emmaroboto',
        'emma.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174010');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174011',
        'Olivia Roboto',
        'oliviaroboto',
        'olivia.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174011');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174012',
        'Mateus Roboto',
        'mateusroboto',
        'mateus.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174012');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174013',
        'Carl Roboto',
        'carlroboto',
        'carl.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174013');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174014',
        'Indigo Roboto',
        'indigoroboto',
        'indigo.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174014');
INSERT INTO account (
    id,
    name,
    username,
    email,
    bio,
    deleted,
    is_private,
    validated,
    created_at,
    publication_count,
    follower_count,
    following_count
)
SELECT  '123e4567-e89b-12d3-a456-426614174015',
        'Paola Roboto',
        'paolaroboto',
        'paola.roboto@example.com',
        'Testing bio',
        false,
        false,
        true,
        '2025-08-04T03:00:45.712174Z',
        0,
        0,
        0
    WHERE NOT EXISTS (SELECT 1
            FROM account
            WHERE id = '123e4567-e89b-12d3-a456-426614174015');
