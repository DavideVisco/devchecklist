CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS checklist_templates (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   name VARCHAR(255),
    description TEXT,
    user_id BIGINT NOT NULL REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS checklist_item_templates (
                                                        id BIGSERIAL PRIMARY KEY,
                                                        description TEXT,
                                                        item_order INTEGER,
                                                        template_id BIGINT REFERENCES checklist_templates(id)
    );

CREATE TABLE IF NOT EXISTS checklist_runs (
                                              id BIGSERIAL PRIMARY KEY,
                                              name VARCHAR(255),
    template_id BIGINT REFERENCES checklist_templates(id),
    user_id BIGINT NOT NULL REFERENCES users(id),
    created_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS checklist_item_runs (
                                                   id BIGSERIAL PRIMARY KEY,
                                                   description TEXT,
                                                   item_order INTEGER,
                                                   done BOOLEAN,
                                                   run_id BIGINT REFERENCES checklist_runs(id)
    );

INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$CwTycUXWue0Thq9StjUM0uJ8DEk.Ue1koXSPoaqe7/jH730cdpaye');

INSERT INTO checklist_templates (name, description, user_id)
VALUES ('Release Checklist', 'Checklist for releases', 1);

INSERT INTO checklist_item_templates (description, item_order, template_id)
VALUES
    ('Build passes', 1, 1),
    ('Tag version', 2, 1);