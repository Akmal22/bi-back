-- liquibase formatted sql

-- Astana Hub:          founded 2017 → years 2017-2021 missing
-- Berlin Startup Factory: founded 2014 → years 2014-2021 missing
-- Silicon Starters:    founded 2012 → years 2012-2021 missing
-- 2026 added for all three incubators

--changeset akmal:seed-incubator-projects-historical
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_projects WHERE year < 2022
INSERT INTO incubator_projects (incubator_id, year, projects_count, fund) VALUES
    -- Astana Hub 2017-2021
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2017,  3,   120000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2018,  6,   240000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2019,  9,   400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2020, 12,   580000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2021, 15,   740000.00),
    -- Berlin Startup Factory 2014-2021
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2014,  5,   400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2015,  9,   650000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2016, 13,   980000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2017, 17,  1280000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2018, 21,  1620000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2019, 24,  1900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2020, 27,  2200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2021, 31,  2530000.00),
    -- Silicon Starters 2012-2021
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2012,  9,   900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2013, 14,  1450000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2014, 19,  2000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2015, 25,  2700000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2016, 31,  3400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2017, 37,  4200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2018, 43,  5000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2019, 48,  5900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2020, 51,  6500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2021, 56,  7700000.00);
--rollback DELETE FROM incubator_projects WHERE year < 2022;

--changeset akmal:seed-incubator-projects-2026
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_projects WHERE year = 2026
INSERT INTO incubator_projects (incubator_id, year, projects_count, fund) VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2026,  45,  2900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2026,  68,  5900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2026, 115, 22000000.00);
--rollback DELETE FROM incubator_projects WHERE year = 2026;

--changeset akmal:seed-incubator-residents-historical
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_residents WHERE year < 2022
INSERT INTO incubator_residents (incubator_id, year, incubated_companies, failed_companies, graduated_companies, received_application, accepted_application, active_after_3_months, active_after_6_months, active_after_1_year, active_after_3_years, active_after_5_years, failed_after_3_months, failed_after_6_months, failed_after_1_year, failed_after_3_years, failed_after_5_years)
VALUES
    -- Astana Hub 2017-2021
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2017,  3, 0,  0,  18,  4,  3,  3,  2, 1, 0, 0, 0, 0, 1, 1),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2018,  6, 1,  1,  30,  7,  5,  5,  4, 3, 2, 0, 0, 1, 1, 2),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2019,  9, 2,  2,  44, 10,  8,  7,  6, 4, 3, 1, 1, 1, 2, 3),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2020, 12, 2,  3,  60, 14, 11, 10,  8, 6, 4, 1, 1, 2, 3, 4),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2021, 14, 3,  5,  78, 18, 14, 12, 11, 8, 6, 1, 2, 2, 4, 5),
    -- Berlin Startup Factory 2014-2021
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2014,  6, 1,  2,  32,  8,  6,  5,  4, 3, 2, 0, 1, 1, 2, 3),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2015,  9, 2,  3,  50, 12,  9,  8,  7, 5, 4, 1, 1, 2, 3, 4),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2016, 13, 2,  5,  68, 16, 12, 11,  9, 7, 5, 1, 1, 3, 4, 6),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2017, 17, 3,  7,  88, 21, 16, 14, 13, 10, 7, 1, 2, 3, 5, 7),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2018, 21, 4,  8, 110, 26, 20, 18, 16, 13, 9, 1, 2, 4, 6, 9),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2019, 25, 4, 10, 135, 31, 24, 22, 19, 15, 11, 2, 2, 5, 7, 10),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2020, 28, 5, 12, 158, 36, 28, 25, 22, 18, 13, 2, 3, 5, 7, 11),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2021, 32, 6, 13, 175, 41, 31, 28, 25, 21, 15, 2, 3, 6, 8, 12),
    -- Silicon Starters 2012-2021
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2012,  9, 1,  3,  48, 11,  9,  8,  7, 5, 3, 1, 1, 1, 2, 3),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2013, 14, 2,  5,  72, 17, 13, 12, 10, 8, 5, 1, 1, 2, 3, 4),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2014, 19, 3,  8,  98, 23, 18, 16, 14, 11, 8, 1, 2, 3, 4, 6),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2015, 24, 4, 10, 128, 29, 23, 21, 18, 14, 10, 1, 2, 4, 5, 7),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2016, 30, 5, 13, 162, 36, 29, 26, 23, 18, 13, 2, 2, 4, 7, 9),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2017, 36, 6, 16, 200, 44, 35, 32, 28, 23, 16, 2, 3, 5, 8, 11),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2018, 41, 6, 19, 238, 50, 40, 36, 32, 26, 19, 2, 3, 6, 9, 13),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2019, 46, 7, 22, 275, 56, 45, 41, 36, 30, 21, 3, 4, 7, 11, 15),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2020, 51, 8, 25, 315, 62, 49, 45, 40, 33, 24, 3, 4, 8, 12, 18),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2021, 56, 8, 26, 340, 68, 54, 49, 44, 36, 26, 3, 4, 9, 14, 20);
--rollback DELETE FROM incubator_residents WHERE year < 2022;

--changeset akmal:seed-incubator-residents-2026
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_residents WHERE year = 2026
INSERT INTO incubator_residents (incubator_id, year, incubated_companies, failed_companies, graduated_companies, received_application, accepted_application, active_after_3_months, active_after_6_months, active_after_1_year, active_after_3_years, active_after_5_years, failed_after_3_months, failed_after_6_months, failed_after_1_year, failed_after_3_years, failed_after_5_years)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2026,  45,  7, 17, 200,  50,  42,  38,  34, 27, 20,  3,  4,  7, 10, 15),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2026,  68, 10, 32, 320,  78,  64,  60,  55, 46, 36,  4,  6, 10, 17, 27),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2026, 115, 15, 58, 640, 135, 108, 102,  94, 78, 58,  7,  9, 17, 30, 47);
--rollback DELETE FROM incubator_residents WHERE year = 2026;

--changeset akmal:seed-incubator-income-historical
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_income WHERE year < 2022
INSERT INTO incubator_income (incubator_id, year, initial_capital, paid_services_income, paid_training_income, paid_facilities_income, donors, state, loans)
VALUES
    -- Astana Hub 2017-2021
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2017, 500000.00,   5000.00,  1500.00,  2000.00,  18000.00, 120000.00, 130000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2018, 500000.00,  10000.00,  3500.00,  4500.00,  32000.00, 145000.00, 110000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2019, 500000.00,  18000.00,  7000.00,  9000.00,  55000.00, 168000.00,  95000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2020, 500000.00,  28000.00, 11000.00, 14000.00,  78000.00, 195000.00,  80000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2021, 500000.00,  37000.00, 15000.00, 19000.00,  98000.00, 225000.00,  65000.00),
    -- Berlin Startup Factory 2014-2021
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2014, 1200000.00,  14000.00,  5000.00,  6500.00,  42000.00, 185000.00, 230000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2015, 1200000.00,  24000.00,  9000.00, 11000.00,  68000.00, 215000.00, 215000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2016, 1200000.00,  38000.00, 14000.00, 18000.00,  98000.00, 250000.00, 195000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2017, 1200000.00,  52000.00, 20000.00, 25000.00, 130000.00, 285000.00, 175000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2018, 1200000.00,  68000.00, 27000.00, 34000.00, 168000.00, 320000.00, 155000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2019, 1200000.00,  85000.00, 35000.00, 44000.00, 208000.00, 358000.00, 135000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2020, 1200000.00, 104000.00, 43000.00, 55000.00, 270000.00, 395000.00, 115000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2021, 1200000.00, 118000.00, 50000.00, 63000.00, 315000.00, 425000.00, 108000.00),
    -- Silicon Starters 2012-2021
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2012, 3000000.00,  14000.00,  5000.00,  7000.00,  32000.00,      0.00, 320000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2013, 3000000.00,  28000.00, 10000.00, 14000.00,  58000.00,      0.00, 360000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2014, 3000000.00,  48000.00, 18000.00, 25000.00,  92000.00,      0.00, 395000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2015, 3000000.00,  72000.00, 28000.00, 38000.00, 135000.00,      0.00, 410000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2016, 3000000.00, 102000.00, 42000.00, 56000.00, 185000.00,      0.00, 405000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2017, 3000000.00, 138000.00, 58000.00, 78000.00, 248000.00,      0.00, 395000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2018, 3000000.00, 182000.00, 78000.00, 105000.00, 322000.00,     0.00, 380000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2019, 3000000.00, 228000.00, 100000.00, 136000.00, 400000.00,    0.00, 360000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2020, 3000000.00, 285000.00, 128000.00, 170000.00, 510000.00,    0.00, 330000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2021, 3000000.00, 335000.00, 155000.00, 195000.00, 680000.00,    0.00, 295000.00);
--rollback DELETE FROM incubator_income WHERE year < 2022;

--changeset akmal:seed-incubator-income-2026
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_income WHERE year = 2026
INSERT INTO incubator_income (incubator_id, year, initial_capital, paid_services_income, paid_training_income, paid_facilities_income, donors, state, loans)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2026,  500000.00, 120000.00,  55000.00,  65000.00,  245000.00, 400000.00,  10000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2026, 1200000.00, 330000.00, 150000.00, 185000.00,  610000.00, 700000.00,  20000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2026, 3000000.00, 950000.00, 455000.00, 570000.00, 2000000.00,      0.00,  80000.00);
--rollback DELETE FROM incubator_income WHERE year = 2026;

--changeset akmal:seed-incubator-investment-historical
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_investment WHERE year < 2022
INSERT INTO incubator_investment (incubator_id, year, seed, state, privates, current_year_investment, cumulative_investment)
VALUES
    -- Astana Hub 2017-2021
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2017,  20000.00,  40000.00,  10000.00,   70000.00,   70000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2018,  40000.00,  70000.00,  20000.00,  130000.00,  200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2019,  70000.00, 110000.00,  40000.00,  220000.00,  420000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2020, 100000.00, 160000.00,  60000.00,  320000.00,  680000.00),
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2021, 150000.00, 230000.00, 100000.00,  480000.00,  950000.00),
    -- Berlin Startup Factory 2014-2021
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2014, 120000.00,  80000.00, 100000.00,  300000.00,  300000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2015, 180000.00, 130000.00, 160000.00,  470000.00,  800000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2016, 240000.00, 180000.00, 230000.00,  650000.00, 1500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2017, 310000.00, 240000.00, 310000.00,  860000.00, 2400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2018, 380000.00, 300000.00, 420000.00, 1100000.00, 3500000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2019, 450000.00, 370000.00, 550000.00, 1370000.00, 4900000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2020, 520000.00, 440000.00, 680000.00, 1640000.00, 6400000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2021, 600000.00, 520000.00, 800000.00, 1920000.00, 8200000.00),
    -- Silicon Starters 2012-2021
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2012,  250000.00, 0.00,   380000.00,   630000.00,   630000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2013,  360000.00, 0.00,   600000.00,   960000.00,  1600000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2014,  500000.00, 0.00,   900000.00,  1400000.00,  3000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2015,  680000.00, 0.00,  1300000.00,  1980000.00,  5000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2016,  880000.00, 0.00,  1750000.00,  2630000.00,  7600000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2017, 1100000.00, 0.00,  2250000.00,  3350000.00, 11000000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2018, 1350000.00, 0.00,  2800000.00,  4150000.00, 15200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2019, 1600000.00, 0.00,  3400000.00,  5000000.00, 20200000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2020, 1900000.00, 0.00,  4100000.00,  6000000.00, 26300000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2021, 2200000.00, 0.00,  5000000.00,  7200000.00, 33600000.00);
--rollback DELETE FROM incubator_investment WHERE year < 2022;

--changeset akmal:seed-incubator-investment-2026
--preconditions onFail:MARK_RAN onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM incubator_investment WHERE year = 2026
INSERT INTO incubator_investment (incubator_id, year, seed, state, privates, current_year_investment, cumulative_investment)
VALUES
    ((SELECT id FROM incubator WHERE uuid = 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'), 2026,  600000.00,  680000.00,   650000.00,  1930000.00,  6770000.00),
    ((SELECT id FROM incubator WHERE uuid = 'b2c3d4e5-f6a7-8901-bcde-f12345678901'), 2026, 1600000.00, 1000000.00,  2700000.00,  5300000.00, 24450000.00),
    ((SELECT id FROM incubator WHERE uuid = 'c3d4e5f6-a7b8-9012-cdef-123456789012'), 2026, 6500000.00,       0.00, 17800000.00, 24300000.00, 113800000.00);
--rollback DELETE FROM incubator_investment WHERE year = 2026;
