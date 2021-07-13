USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-26h}'), 'test', 500);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, readTime) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-28h}'), 'test', 1000);