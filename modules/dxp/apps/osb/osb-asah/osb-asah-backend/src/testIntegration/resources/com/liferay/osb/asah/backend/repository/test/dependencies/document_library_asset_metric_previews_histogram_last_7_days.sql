USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary (assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-28h}'), 'test', 2);
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-30h}'), 'test', 1)