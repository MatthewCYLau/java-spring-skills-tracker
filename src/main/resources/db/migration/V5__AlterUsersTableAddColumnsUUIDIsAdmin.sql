ALTER TABLE users
ADD COLUMN user_id UUID PRIMARY KEY NOT NULL,
ADD COLUMN isAdmin BOOLEAN NOT NULL;