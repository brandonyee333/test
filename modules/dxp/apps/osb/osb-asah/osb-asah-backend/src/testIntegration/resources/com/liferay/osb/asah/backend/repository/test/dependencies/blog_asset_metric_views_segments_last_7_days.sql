USE hive.default;

DELETE FROM Blog;

INSERT INTO Blog (assetId, channelId, eventDate, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'test', ARRAY ['A', 'B'], 1);
INSERT INTO Blog (assetId, channelId, eventDate, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-5d}'), 'test', ARRAY ['B', 'C'], 2);
INSERT INTO Blog (assetId, channelId, eventDate, projectId, segmentNames, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10d}'), 'test', ARRAY ['A', 'B', 'C'], 3);