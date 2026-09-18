ALTER TABLE users ADD COLUMN IF NOT EXISTS business_type VARCHAR(50);

CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS role_menu_permissions (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    menu_key VARCHAR(100) NOT NULL,
    can_access BOOLEAN NOT NULL,
    UNIQUE (role_id, menu_key)
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    role_id BIGINT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Seed: default roles
INSERT INTO roles (name, description) VALUES
  ('SUPER_ADMIN', 'Full access to all menus and all business units'),
  ('PROJECT', 'Project business unit access'),
  ('SALES', 'Sales business unit access'),
  ('OPS', 'Operations business unit access'),
  ('SERVICE', 'Service business unit access')
ON CONFLICT (name) DO NOTHING;