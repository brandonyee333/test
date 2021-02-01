CREATE TABLE IF NOT EXISTS Channel (	
    id BIGSERIAL PRIMARY KEY,
    createDate TIMESTAMPTZ,
    name TEXT
);

CREATE TABLE IF NOT EXISTS ChannelDataSource (	
    channelId BIGINT REFERENCES Channel ON DELETE CASCADE,
    dataSourceId BIGINT,
    groupIds BIGINT[],
    PRIMARY KEY (channelId, dataSourceId)
);

CREATE TABLE IF NOT EXISTS Preference (	
    id BIGSERIAL PRIMARY KEY,
    key TEXT UNIQUE,
    value TEXT
);