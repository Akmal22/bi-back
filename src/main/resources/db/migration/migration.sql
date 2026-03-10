-- liquibase formatted sql

--changeset akmal:create-users-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'users'
CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    username  VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(256) NOT NULL,
    full_name VARCHAR(256),
    email     VARCHAR(100) NOT NULL UNIQUE,
    role      VARCHAR(16)  NOT NULL,
    enabled   BOOLEAN      NOT NULL
);
--rollback DROP TABLE IF EXISTS users;

--changeset akmal:add-admin-to-users
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM USERS WHERE USERNAME = 'akmal9433@gmail.com';
insert into users(username, password, full_name, email, role, enabled) values ('admin', '$2a$10$K4EjzB8ufiFVB/m2S8shq.Dq9OI3mbNThyGCCwMwtwa4UK5S9UahK', 'Пепе Пепе', 'akmal9433@gmail.com', 'ADMIN', true);
--rollback DELETE FROM USERS WHERE USERNAME = 'Admin';

--changeset akmal:create-country-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'country'
CREATE TABLE country
(
    id            BIGSERIAL PRIMARY KEY,
    country_code  VARCHAR(2)   NOT NULL UNIQUE,
    country_name  VARCHAR(128) NOT NULL UNIQUE,
    currency_code NUMERIC(3),
    currency_name VARCHAR(256)
);
CREATE INDEX idx_country_code ON country (country_code);
--rollback DROP TABLE IF EXISTS country;

--changeset akmal:create-incubator-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator'
CREATE TABLE incubator
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(64)                    NOT NULL UNIQUE,
    uuid        VARCHAR(36)                    NOT NULL UNIQUE,
    description VARCHAR(256)                   NOT NULL,
    manager     BIGINT REFERENCES users (id)   NOT NULL,
    country     BIGINT REFERENCES country (id) NOT NULL,
    founded     TIMESTAMP                      NOT NULL
);
CREATE INDEX idx_incubator_country ON incubator (country);
CREATE INDEX idx_incubator_manager ON incubator (manager);
--rollback DROP TABLE IF EXISTS incubator;

--changeset akmal:create-incubator-projects-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_projects'
CREATE TABLE incubator_projects
(
    id           BIGSERIAL PRIMARY KEY,
    incubator_id BIGINT REFERENCES incubator (id) NOT NULL,
    year         INTEGER                          NOT NULL CHECK (year BETWEEN 1970 AND EXTRACT (YEAR FROM CURRENT_DATE):: INT),
    projects_count INTEGER                          NOT NULL,
    fund           NUMERIC(19,2)                      NOT NULL
);
CREATE INDEX idx_incubator_projects_incubator_id ON incubator_projects(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_projects;

--changeset akmal:create-incubator-characteristics-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_characteristics'
CREATE TABLE incubator_characteristics
(
    id                             BIGSERIAL PRIMARY KEY,
    incubator_id                   BIGINT REFERENCES incubator (id) NOT NULL,
    average_employees              VARCHAR(128)                     NOT NULL,
    share_amount                   VARCHAR(128)                     NOT NULL,
    total_staff                    INTEGER                          NOT NULL,
    experts_and_consultants        INTEGER                          NOT NULL,
    managers                       INTEGER                          NOT NULL,
    monitoring_and_data_collecting BOOLEAN DEFAULT FALSE,
    requirements                   VARCHAR(1024)
);
CREATE INDEX idx_incubator_characteristics_incubator_id ON incubator_characteristics(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_characteristics;

--changeset akmal:create-incubator-residents-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_residents'
CREATE TABLE incubator_residents
(
    id           BIGSERIAL PRIMARY KEY,
    incubator_id BIGINT REFERENCES incubator (id) NOT NULL,
    year         INTEGER                          NOT NULL CHECK (year BETWEEN 1970 AND EXTRACT (YEAR FROM CURRENT_DATE):: INT
) ,
    incubated_companies   NUMERIC(22) NOT NULL,
    failed_companies      NUMERIC(22) NOT NULL,
    graduated_companies   NUMERIC(22) NOT NULL,
    received_application  NUMERIC(22) NOT NULL,
    accepted_application  NUMERIC(22) NOT NULL,
    active_after_3_months NUMERIC(22) NOT NULL,
    active_after_6_months NUMERIC(22) NOT NULL,
    active_after_1_year   NUMERIC(22) NOT NULL,
    active_after_3_years  NUMERIC(22) NOT NULL,
    active_after_5_years  NUMERIC(22) NOT NULL,
    failed_after_3_months NUMERIC(22) NOT NULL,
    failed_after_6_months NUMERIC(22) NOT NULL,
    failed_after_1_year   NUMERIC(22) NOT NULL,
    failed_after_3_years  NUMERIC(22) NOT NULL,
    failed_after_5_years  NUMERIC(22) NOT NULL
);
CREATE INDEX idx_incubator_residents_incubator_id ON incubator_residents(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_residents;

--changeset akmal:create-incubator-infrastructure-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_infrastructure'
CREATE TABLE incubator_infrastructure
(
    id                 BIGSERIAL PRIMARY KEY,
    incubator_id       BIGINT REFERENCES incubator (id) NOT NULL,
    sectors_covered    INTEGER                          NOT NULL,
    years_in_operation INTEGER                          NOT NULL,
    programme_duration INTEGER                          NOT NULL
);
CREATE INDEX idx_incubator_infrastructure_incubator_id ON incubator_infrastructure(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_infrastructure;

--changeset akmal:create-incubator-space-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_space'
CREATE TABLE incubator_space
(
    id                   BIGSERIAL PRIMARY KEY,
    incubator_id         BIGINT REFERENCES incubator (id) NOT NULL,
    overall_space        INTEGER                          NOT NULL,
    avg_resident_space   INTEGER                          NOT NULL,
    communal_space       INTEGER                          NOT NULL,
    admin_space          INTEGER                          NOT NULL,
    communal_space_ratio INTEGER                          NOT NULL CHECK (communal_space_ratio BETWEEN 0 AND 100)
);
CREATE INDEX idx_incubator_space_incubator_id ON incubator_space(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_space;

--changeset akmal:create-incubator-services-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_services'
CREATE TABLE incubator_services
(
    id                 BIGSERIAL PRIMARY KEY,
    incubator_id       BIGINT REFERENCES incubator (id) NOT NULL,
    offered_services   INTEGER                          NOT NULL,
    free_services      INTEGER                          NOT NULL,
    paid_services      INTEGER                          NOT NULL,
    used_services      INTEGER                          NOT NULL,
    offered_facilities INTEGER                          NOT NULL,
    free_facilities    INTEGER                          NOT NULL,
    paid_facilities    INTEGER                          NOT NULL,
    used_facilities    INTEGER                          NOT NULL,
    offered_trainings  INTEGER                          NOT NULL,
    free_trainings     INTEGER                          NOT NULL,
    paid_trainings     INTEGER                          NOT NULL,
    used_trainings     INTEGER                          NOT NULL
);
CREATE INDEX idx_incubator_services_incubator_id ON incubator_services(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_services;

--changeset akmal:create-incubator-income-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_income'
CREATE TABLE incubator_income
(
    id           BIGSERIAL PRIMARY KEY,
    incubator_id BIGINT REFERENCES incubator (id) NOT NULL,
    year         INTEGER                          NOT NULL CHECK (year BETWEEN 1970 AND EXTRACT (YEAR FROM CURRENT_DATE):: INT
) ,
    initial_capital        NUMERIC(19, 2)                   NOT NULL,
    paid_services_income   NUMERIC(19, 2)                   NOT NULL,
    paid_training_income   NUMERIC(19, 2)                   NOT NULL,
    paid_facilities_income NUMERIC(19, 2)                   NOT NULL,
    donors                 NUMERIC(19, 2)                   NOT NULL,
    state                  NUMERIC(19, 2)                   NOT NULL,
    loans                  NUMERIC(19, 2)                   NOT NULL
);
CREATE INDEX idx_incubator_income_incubator_id ON incubator_income(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_income;

--changeset akmal:create-incubator-investment-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_investment'
CREATE TABLE incubator_investment
(
    id           BIGSERIAL PRIMARY KEY,
    incubator_id BIGINT REFERENCES incubator (id) NOT NULL,
    year         INTEGER                          NOT NULL CHECK (year BETWEEN 1970 AND EXTRACT (YEAR FROM CURRENT_DATE):: INT
) ,
    seed                    NUMERIC(19, 2)                   NOT NULL,
    state                   NUMERIC(19, 2)                   NOT NULL,
    privates                NUMERIC(19, 2)                   NOT NULL,
    current_year_investment NUMERIC(19, 2)                   NOT NULL,
    cumulative_investment   NUMERIC(19, 2)                   NOT NULL
);
CREATE INDEX idx_incubator_investment_incubator_id ON incubator_investment(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_investment;

--changeset akmal:create-incubator-expenses-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'incubator_expenses'
CREATE TABLE incubator_expenses
(
    id              BIGSERIAL PRIMARY KEY,
    incubator_id    BIGINT REFERENCES incubator (id) NOT NULL,
    payroll         NUMERIC(19, 2)                   NOT NULL,
    equipment       NUMERIC(19, 2)                   NOT NULL,
    utilities       NUMERIC(19, 2)                   NOT NULL,
    tax             NUMERIC(19, 2)                   NOT NULL,
    rents           NUMERIC(19, 2)                   NOT NULL,
    bank_repayments NUMERIC(19, 2)                   NOT NULL,
    material        NUMERIC(19, 2)                   NOT NULL,
    insurance       NUMERIC(19, 2)                   NOT NULL
);
CREATE INDEX idx_incubator_expenses_incubator_id ON incubator_expenses(incubator_id);
--rollback DROP TABLE IF EXISTS incubator_expenses;

--changeset akmal:create-spring-session-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'spring_session'
CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID),
                                CONSTRAINT SPRING_SESSION_UK UNIQUE (SESSION_ID)
);

CREATE INDEX SPRING_SESSION_IX1
    ON SPRING_SESSION (EXPIRY_TIME);

CREATE INDEX SPRING_SESSION_IX2
    ON SPRING_SESSION (PRINCIPAL_NAME);
--rollback DROP TABLE IF EXISTS SPRING_SESSION;

--changeset akmal:create-spring-session-attributes-table
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'spring_session_attributes'
CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES BYTEA NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK
                                               PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK
                                               FOREIGN KEY (SESSION_PRIMARY_ID)
                                                   REFERENCES SPRING_SESSION (PRIMARY_ID)
                                                   ON DELETE CASCADE
);
--rollback DROP TABLE IF EXISTS SPRING_SESSION_ATTRIBUTES;