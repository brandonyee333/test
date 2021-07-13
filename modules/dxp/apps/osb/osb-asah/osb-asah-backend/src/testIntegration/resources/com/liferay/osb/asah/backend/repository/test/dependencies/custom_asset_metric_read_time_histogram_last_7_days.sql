USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '26' HOUR), 'test', 500);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '28' HOUR), 'test', 1000);