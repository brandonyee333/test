USE hive.default;

DELETE FROM CustomAsset;

INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now()), 'test', 1);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '10' HOUR), 'test', 4);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '7' HOUR), 'test', 2);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '10' HOUR), 'test_2', 2);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', now() - INTERVAL '8' HOUR), 'test', 7);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', now() - INTERVAL '8' HOUR), 'test', 1);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '25' HOUR), 'test', 4);
INSERT INTO CustomAsset(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '26' HOUR), 'test', 2);