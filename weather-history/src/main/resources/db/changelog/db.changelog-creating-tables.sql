CREATE TABLE "account"
(
    "id"         BIGSERIAL NOT NULL,
    "email"      VARCHAR(255),
    "first_name" VARCHAR(255),
    "last_name"  VARCHAR(255),
    "password"   VARCHAR(255),
    "role"       VARCHAR(255),
    CONSTRAINT "account_pkey" PRIMARY KEY ("id")
);

ALTER TABLE "account"
    ADD CONSTRAINT "uk_q0uja26qgu1atulenwup9rxyr" UNIQUE ("email");
