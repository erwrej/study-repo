CREATE TABLE IF NOT EXISTS browser_page_statistics (
    page_id BIGINT NOT NULL UNIQUE,
    views bigint
);
