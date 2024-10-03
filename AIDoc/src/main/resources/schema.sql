CREATE TABLE IF NOT EXISTS users (
    userEmail VARCHAR(255) PRIMARY KEY,
    userID UUID,
    userName VARCHAR(255),
    userPhone VARCHAR(255)
    
);
ALTER TABLE users ADD COLUMN IF NOT EXISTS "userPassword" VARCHAR(255);