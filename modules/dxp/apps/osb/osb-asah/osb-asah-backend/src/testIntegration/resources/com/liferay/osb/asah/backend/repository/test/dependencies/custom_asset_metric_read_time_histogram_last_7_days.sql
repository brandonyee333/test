USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${today-2h}'), 'test', 500);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'test', 1000);