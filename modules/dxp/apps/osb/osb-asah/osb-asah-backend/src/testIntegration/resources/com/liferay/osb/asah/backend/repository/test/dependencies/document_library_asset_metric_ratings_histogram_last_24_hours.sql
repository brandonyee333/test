USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}'), 'test', 1);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '10' HOUR), 'test', 4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '7' HOUR), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '10' HOUR), 'test_2', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now}' - INTERVAL '8' HOUR), 'test', 7);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 2, date_trunc('HOUR', timestamp '${now}' - INTERVAL '8' HOUR), 'test', 1);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '25' HOUR), 'test', 4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '26' HOUR), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '26' HOUR), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e231fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '5' HOUR), 'test',  4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e231fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '6' HOUR), 'test', 2);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e231fabc', 2, date_trunc('HOUR', timestamp '${now}' - INTERVAL '5' HOUR), 'test', 4);
INSERT INTO DocumentLibrary(assetId, channelId, eventDate, projectId, ratingsScore) VALUES ('e231fabc', 1, date_trunc('HOUR', timestamp '${now}' - INTERVAL '25' HOUR), 'test', 2);