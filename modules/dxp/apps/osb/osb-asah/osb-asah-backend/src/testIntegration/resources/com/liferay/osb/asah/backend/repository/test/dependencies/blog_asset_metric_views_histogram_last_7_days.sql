USE hive.default;

DELETE FROM Blog;

INSERT INTO Blog (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '28' HOUR), 'test', 2);
INSERT INTO Blog (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '30' HOUR), 'test', 1);