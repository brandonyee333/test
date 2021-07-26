USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 1, 1, 'test', ARRAY ['A', 'B', 'C']);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-5d}'), 1, 2, 'test', ARRAY ['A', 'B', 'C']);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10d}'), 2, 3, 'test', ARRAY []);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-15d}'), 2, 4, 'test', ARRAY []);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-25h}'), 3, 5, 'test', ARRAY []);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 4, 6, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 5, 0, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-31d}'), 1, 7, 'test', ARRAY ['A', 'B', 'C']);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId, segmentNames) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-40d}'), 1, 8, 'test', ARRAY []);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-45d}'), 1, 9, 'test');