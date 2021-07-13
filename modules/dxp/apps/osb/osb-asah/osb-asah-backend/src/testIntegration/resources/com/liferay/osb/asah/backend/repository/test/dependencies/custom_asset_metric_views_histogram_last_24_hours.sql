USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}'), 'test', 1);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10h}'), 'test', 4);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-7h}'), 'test', 2);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10h}'), 'test_2', 2);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now-8h}'), 'test', 7);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now-8h}'), 'test', 1);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-25h}' ), 'test', 4);
INSERT INTO CustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-26h}'), 'test', 2);