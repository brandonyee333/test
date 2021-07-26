USE hive.default;

DELETE FROM Form;

INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 1, 'test', ARRAY ['A', 'B', 'C'], 1);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-5d}'), 1, 'test', ARRAY ['A', 'B', 'C'], 2);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10d}'), 2, 'test', ARRAY [], 3);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-15d}'), 2, 'test', ARRAY [], 4);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-25h}'), 3, 'test', ARRAY [], 5);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 4, 'test', 6);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 5, 'test', 0);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-31d}'), 1, 'test', ARRAY ['A', 'B', 'C'], 7);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-40d}'), 1, 'test', ARRAY [], 8);
INSERT INTO Form (assetId, channelId, eventDate, individualId, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-45d}'), 1, 'test', 9);