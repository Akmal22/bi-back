-- liquibase formatted sql

--changeset akmal:seed-countries
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM country
INSERT INTO country (country_code, country_name, currency_code, currency_name) VALUES
    ('KZ', 'Kazakhstan',     398, 'Kazakhstani Tenge'),
    ('DE', 'Germany',        978, 'Euro'),
    ('US', 'United States',  840, 'US Dollar');
--rollback DELETE FROM country WHERE country_code IN ('KZ', 'DE', 'US');

--changeset akmal:seed-manager-users
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM users WHERE username IN ('manager_kz', 'manager_de', 'manager_us')
INSERT INTO users (username, password, full_name, email, role, enabled) VALUES
    ('manager_kz', '$2a$10$K4EjzB8ufiFVB/m2S8shq.Dq9OI3mbNThyGCCwMwtwa4UK5S9UahK', 'Алибек Жаксыбеков', 'manager.kz@example.com',  'MANAGER', true),
    ('manager_de', '$2a$10$K4EjzB8ufiFVB/m2S8shq.Dq9OI3mbNThyGCCwMwtwa4UK5S9UahK', 'Hans Müller',        'manager.de@example.com',  'MANAGER', true),
    ('manager_us', '$2a$10$K4EjzB8ufiFVB/m2S8shq.Dq9OI3mbNThyGCCwMwtwa4UK5S9UahK', 'John Carter',        'manager.us@example.com',  'MANAGER', true);
--rollback DELETE FROM users WHERE username IN ('manager_kz', 'manager_de', 'manager_us');

--changeset akmal:seed-incubators
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator
INSERT INTO incubator (name, uuid, description, manager, country, founded) VALUES
    ('Astana Hub',
     'a1b2c3d4-e5f6-7890-abcd-ef1234567890',
     'Leading technology incubator in Kazakhstan supporting early-stage startups in fintech and agritech.',
     (SELECT id FROM users WHERE username = 'manager_kz'),
     (SELECT id FROM country WHERE country_code = 'KZ'),
     '2017-03-15 00:00:00'),
    ('Berlin Startup Factory',
     'b2c3d4e5-f6a7-8901-bcde-f12345678901',
     'Berlin-based incubator focused on deep-tech, SaaS and green energy startups.',
     (SELECT id FROM users WHERE username = 'manager_de'),
     (SELECT id FROM country WHERE country_code = 'DE'),
     '2014-06-01 00:00:00'),
    ('Silicon Starters',
     'c3d4e5f6-a7b8-9012-cdef-123456789012',
     'San Francisco incubator accelerating B2B software and AI startups to Series A.',
     (SELECT id FROM users WHERE username = 'manager_us'),
     (SELECT id FROM country WHERE country_code = 'US'),
     '2012-01-10 00:00:00');
--rollback DELETE FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012');

--changeset akmal:seed-incubator-characteristics
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_characteristics
INSERT INTO incubator_characteristics (incubator_id, average_employees, share_amount, total_staff, experts_and_consultants, managers, monitoring_and_data_collecting, requirements)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'),
     'ONE_PER_ONE_RESIDENT', 'BETWEEN_1_AND_10_PERCENT', 18, 6, 3, true,
     'Registered legal entity in Kazakhstan; MVP required; team of at least 2 co-founders.'),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'),
     'MORE_THAN_ONE_PER_ONE_RESIDENT', 'BETWEEN_1_AND_5_PERCENT', 30, 10, 5, true,
     'EU-registered company; prototype stage or beyond; English or German pitch deck.'),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'),
     'MORE_THAN_ONE_PER_ONE_RESIDENT', 'BETWEEN_10_AND_20_PERCENT', 45, 15, 7, true,
     'Delaware C-Corp; working product with early traction; full-time founding team.');
--rollback DELETE FROM incubator_characteristics WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-infrastructure
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_infrastructure
INSERT INTO incubator_infrastructure (incubator_id, sectors_covered, years_in_operation, programme_duration)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 4,  7, 6),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 6, 10, 9),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 8, 12, 12);
--rollback DELETE FROM incubator_infrastructure WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-space
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_space
INSERT INTO incubator_space (incubator_id, overall_space, avg_resident_space, communal_space, admin_space, communal_space_ratio)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 1200, 25, 360, 120, 30),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2500, 30, 750, 250, 30),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 4000, 40, 1200, 400, 30);
--rollback DELETE FROM incubator_space WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-services
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_services
INSERT INTO incubator_services (incubator_id, offered_services, free_services, paid_services, used_services, offered_facilities, free_facilities, paid_facilities, used_facilities, offered_trainings, free_trainings, paid_trainings, used_trainings)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 12, 8, 4, 10, 6, 4, 2,  5,  8, 5, 3,  7),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 20, 12, 8, 17, 10, 6, 4,  9, 14, 8, 6, 12),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 30, 15, 15, 25, 14, 8, 6, 12, 20, 10, 10, 18);
--rollback DELETE FROM incubator_services WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-expenses
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_expenses
INSERT INTO incubator_expenses (incubator_id, payroll, equipment, utilities, tax, rents, bank_repayments, material, insurance)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'),  85000.00,  12000.00,  8000.00,  6500.00, 24000.00,  5000.00,  3000.00,  2500.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 210000.00,  35000.00, 22000.00, 18000.00, 72000.00, 15000.00,  8000.00,  7000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 480000.00, 110000.00, 55000.00, 62000.00, 180000.00, 40000.00, 20000.00, 18000.00);
--rollback DELETE FROM incubator_expenses WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-projects
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_projects
INSERT INTO incubator_projects (incubator_id, year, projects_count, fund) VALUES
    -- Astana Hub
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2022, 18,   950000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2023, 24,  1350000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2024, 30,  1800000.00),
    -- Berlin Startup Factory
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2022, 35,  2800000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2023, 42,  3500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2024, 50,  4200000.00),
    -- Silicon Starters
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2022, 60,  8500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2023, 72, 11000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2024, 85, 14500000.00);
--rollback DELETE FROM incubator_projects WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-residents
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_residents
INSERT INTO incubator_residents (incubator_id, year, incubated_companies, failed_companies, graduated_companies, received_application, accepted_application, active_after_3_months, active_after_6_months, active_after_1_year, active_after_3_years, active_after_5_years, failed_after_3_months, failed_after_6_months, failed_after_1_year, failed_after_3_years, failed_after_5_years)
VALUES
    -- Astana Hub 2022
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2022, 18, 4, 6, 95, 22, 17, 15, 13, 10, 7, 1, 2,  3, 5, 7),
    -- Astana Hub 2023
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2023, 24, 5, 8, 120, 28, 23, 20, 18, 14, 10, 1, 3, 4, 7, 9),
    -- Astana Hub 2024
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2024, 30, 5, 11, 145, 34, 28, 25, 22, 17, 12, 2, 3, 5, 8, 11),
    -- Berlin Startup Factory 2022
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2022, 35, 6, 14, 180, 42, 33, 30, 27, 22, 16, 2, 3, 6, 9, 13),
    -- Berlin Startup Factory 2023
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2023, 42, 7, 18, 210, 50, 40, 37, 33, 27, 20, 2, 4, 7, 11, 16),
    -- Berlin Startup Factory 2024
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2024, 50, 8, 22, 245, 58, 47, 44, 39, 32, 24, 3, 4, 8, 13, 19),
    -- Silicon Starters 2022
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2022, 60, 9, 28, 350, 72, 57, 52, 46, 38, 28, 3, 5, 10, 16, 23),
    -- Silicon Starters 2023
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2023, 72, 10, 35, 410, 85, 68, 62, 56, 46, 34, 4, 6, 12, 19, 28),
    -- Silicon Starters 2024
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2024, 85, 11, 42, 480, 100, 80, 74, 67, 55, 40, 5, 7, 13, 22, 33);
--rollback DELETE FROM incubator_residents WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-income
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_income
INSERT INTO incubator_income (incubator_id, year, initial_capital, paid_services_income, paid_training_income, paid_facilities_income, donors, state, loans)
VALUES
    -- Astana Hub
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2022,  500000.00, 45000.00, 18000.00, 22000.00, 120000.00, 250000.00,  50000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2023,  500000.00, 62000.00, 25000.00, 30000.00, 150000.00, 280000.00,  40000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2024,  500000.00, 80000.00, 34000.00, 40000.00, 180000.00, 320000.00,  30000.00),
    -- Berlin Startup Factory
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2022, 1200000.00, 130000.00,  55000.00,  70000.00, 350000.00, 450000.00, 100000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2023, 1200000.00, 170000.00,  72000.00,  90000.00, 400000.00, 500000.00,  80000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2024, 1200000.00, 215000.00,  95000.00, 115000.00, 460000.00, 560000.00,  60000.00),
    -- Silicon Starters
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2022, 3000000.00, 380000.00, 180000.00, 220000.00, 900000.00,       0.00, 250000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2023, 3000000.00, 490000.00, 230000.00, 280000.00, 1100000.00,      0.00, 200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2024, 3000000.00, 620000.00, 290000.00, 360000.00, 1350000.00,      0.00, 150000.00);
--rollback DELETE FROM incubator_income WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-investment
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_investment
INSERT INTO incubator_investment (incubator_id, year, seed, state, privates, current_year_investment, cumulative_investment)
VALUES
    -- Astana Hub
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2022,  200000.00,  350000.00, 150000.00,   700000.00,  1200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2023,  280000.00,  420000.00, 220000.00,   920000.00,  2120000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2024,  360000.00,  500000.00, 340000.00,  1200000.00,  3320000.00),
    -- Berlin Startup Factory
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2022,  700000.00,  600000.00,  900000.00,  2200000.00,  8500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2023,  900000.00,  700000.00, 1200000.00,  2800000.00, 11300000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2024, 1100000.00,  800000.00, 1600000.00,  3500000.00, 14800000.00),
    -- Silicon Starters
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2022, 2500000.00,       0.00, 6000000.00,  8500000.00, 45000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2023, 3200000.00,       0.00, 7800000.00, 11000000.00, 56000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2024, 4100000.00,       0.00, 10400000.00, 14500000.00, 70500000.00);
--rollback DELETE FROM incubator_investment WHERE incubator_id IN (SELECT id FROM incubator WHERE uuid IN ('a1b2c3d4-e5f6-7890-abcd-ef1234567890','b2c3d4e5-f6a7-8901-bcde-f12345678901','c3d4e5f6-a7b8-9012-cdef-123456789012'));

--changeset akmal:seed-incubator-projects-2025
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_projects WHERE year = 2025
INSERT INTO incubator_projects (incubator_id, year, projects_count, fund) VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2025, 38,  2400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2025, 60,  5100000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2025, 100, 18500000.00);
--rollback DELETE FROM incubator_projects WHERE year = 2025;

--changeset akmal:seed-incubator-residents-2025
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_residents WHERE year = 2025
INSERT INTO incubator_residents (incubator_id, year, incubated_companies, failed_companies, graduated_companies, received_application, accepted_application, active_after_3_months, active_after_6_months, active_after_1_year, active_after_3_years, active_after_5_years, failed_after_3_months, failed_after_6_months, failed_after_1_year, failed_after_3_years, failed_after_5_years)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2025, 38, 6, 14, 170, 42, 35, 32, 28, 22, 16, 2, 4, 6, 9, 13),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2025, 60, 9, 27, 285, 68, 56, 52, 47, 39, 30, 4, 5, 9, 15, 23),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2025, 100, 13, 50, 560, 118, 94, 88, 80, 66, 49, 6, 8, 15, 26, 40);
--rollback DELETE FROM incubator_residents WHERE year = 2025;

--changeset akmal:seed-incubator-income-2025
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_income WHERE year = 2025
INSERT INTO incubator_income (incubator_id, year, initial_capital, paid_services_income, paid_training_income, paid_facilities_income, donors, state, loans)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2025,  500000.00, 100000.00,  44000.00,  52000.00,  210000.00, 360000.00,  20000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2025, 1200000.00, 270000.00, 122000.00, 148000.00,  530000.00, 630000.00,  40000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2025, 3000000.00, 780000.00, 365000.00, 455000.00, 1650000.00,      0.00, 100000.00);
--rollback DELETE FROM incubator_income WHERE year = 2025;

--changeset akmal:seed-incubator-investment-2025
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_investment WHERE year = 2025
INSERT INTO incubator_investment (incubator_id, year, seed, state, privates, current_year_investment, cumulative_investment)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2025,  460000.00,  580000.00,   480000.00,  1520000.00,  4840000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2025, 1350000.00,  900000.00,  2100000.00,  4350000.00, 19150000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2025, 5200000.00,       0.00, 13800000.00, 19000000.00, 89500000.00);
--rollback DELETE FROM incubator_investment WHERE year = 2025;
