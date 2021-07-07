USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '28' HOUR), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '30' HOUR), 'test', 1)