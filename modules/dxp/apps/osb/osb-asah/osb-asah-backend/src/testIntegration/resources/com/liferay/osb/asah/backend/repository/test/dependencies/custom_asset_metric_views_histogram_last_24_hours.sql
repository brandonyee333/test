INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-4h}'), 'test', 1);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-7h}'), 'test', 2);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10h}'), 'test', 4);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29h}' ), 'test', 4);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-30h}'), 'test', 2);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10h}'), 'test_2', 2);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now-8h}'), 'test', 7);
INSERT INTO BQCustomAsset (assetPrimaryKey, channelId, eventDate, projectId, views) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now-8h}'), 'test', 1);