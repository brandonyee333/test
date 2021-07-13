USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}'), 'test', 1);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'test', 4);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'test', 2);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'test', 2);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'test', 7);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'test', 1);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'test', 4);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'test', 2);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'test', 6);