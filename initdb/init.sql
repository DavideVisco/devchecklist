-- Templates
CREATE TABLE IF NOT EXISTS checklist_templates (
            id BIGSERIAL PRIMARY KEY,
            name VARCHAR(255),
    description TEXT
    );

-- Template Items
CREATE TABLE IF NOT EXISTS checklist_item_templates (
        id BIGSERIAL PRIMARY KEY,
        description TEXT,
        item_order INTEGER,
        template_id BIGINT REFERENCES checklist_templates(id)
    );

-- Runs
CREATE TABLE IF NOT EXISTS checklist_runs (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255),
    template_id BIGINT REFERENCES checklist_templates(id),
    created_at TIMESTAMP
    );

-- Run Items
CREATE TABLE IF NOT EXISTS checklist_item_runs (
        id BIGSERIAL PRIMARY KEY,
        description TEXT,
        item_order INTEGER,
        done BOOLEAN,
        run_id BIGINT REFERENCES checklist_runs(id)
    );

-- Sample data
INSERT INTO checklist_templates (name, description) VALUES ('Release Checklist', 'Seed checklist');
INSERT INTO checklist_item_templates (description, item_order, template_id) VALUES
                                                                                ('Do build', 1, 1),
                                                                                ('Tag version', 2, 1);