CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       cpf VARCHAR(14) NOT NULL UNIQUE,
                       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE accounts (
                          id UUID PRIMARY KEY,
                          user_id UUID NOT NULL REFERENCES users(id),
                          number VARCHAR(36) NOT NULL UNIQUE,
                          type VARCHAR(20) NOT NULL,
                          status VARCHAR(20) NOT NULL,
                          balance DECIMAL(19,2) NOT NULL DEFAULT 0.00,
                          created_at TIMESTAMP NOT NULL DEFAULT NOW()
);