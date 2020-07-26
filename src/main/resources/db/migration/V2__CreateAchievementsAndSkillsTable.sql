CREATE TABLE IF NOT EXISTS skills (
    id UUID PRIMARY KEY NOT NULL,
    skill VARCHAR (100) NOT NULL
);

CREATE TABLE IF NOT EXISTS achievements (
    id UUID PRIMARY KEY NOT NULL,
    profile_id UUID NOT NULL REFERENCES profiles (id),
    skill_id UUID NOT NULL REFERENCES skills (id)
);