USE hive.default;

DELETE FROM Journal;

INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}'), 'test', 1);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'test', 4);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-2d}'), 'test', 2);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-6d}'), 'test', 2);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-8d}'), 'test', 7);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-9d}'), 'test', 1);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'test', 4);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-14d}'), 'test', 2);
INSERT INTO Journal (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'test', 6);