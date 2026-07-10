-- =============================================
-- DDL: Tabel history_pkp untuk audit log PKP
-- Jalankan sekali di database payroll
-- =============================================

CREATE TABLE IF NOT EXISTS history_pkp
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    id_parent    INT          NULL,           -- FK ke master_pkp.id
    value        BIGINT       NULL,           -- nilai lama saat operasi
    tax          VARCHAR(25)  NULL,           -- tax lama
    tax_update   VARCHAR(25)  NULL,           -- tax baru (hanya saat UPDATE)
    n_tax        VARCHAR(25)  NULL,           -- n_tax lama
    n_tax_update VARCHAR(25)  NULL,           -- n_tax baru (hanya saat UPDATE)
    created_by   VARCHAR(60)  NULL,
    created_date DATETIME     NULL,
    status       VARCHAR(10)  NULL            -- 'ADD' atau 'UPDATE'
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_general_ci
  ROW_FORMAT=DYNAMIC;

-- Recommended index untuk performa query history
CREATE INDEX IF NOT EXISTS idx_history_pkp_parent   ON history_pkp (id_parent);
CREATE INDEX IF NOT EXISTS idx_history_pkp_date     ON history_pkp (created_date DESC);
CREATE INDEX IF NOT EXISTS idx_history_pkp_status   ON history_pkp (status);

-- =============================================
-- Recommended index untuk master_pkp (jika belum ada)
-- =============================================
CREATE INDEX IF NOT EXISTS idx_master_pkp_tax       ON master_pkp (tax);
CREATE INDEX IF NOT EXISTS idx_master_pkp_n_tax     ON master_pkp (n_tax);
CREATE INDEX IF NOT EXISTS idx_master_pkp_created_by ON master_pkp (created_by);
