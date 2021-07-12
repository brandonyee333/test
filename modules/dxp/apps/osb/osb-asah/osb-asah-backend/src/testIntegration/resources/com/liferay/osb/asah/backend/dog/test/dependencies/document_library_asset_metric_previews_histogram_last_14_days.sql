USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}'), 'test', 1);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '1' DAY), 'test', 4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '2' DAY), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '6' DAY), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '8' DAY), 'test', 7);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '9' DAY), 'test', 1);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '12' DAY), 'test', 4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '14' DAY), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '15' DAY), 'test', 6);
