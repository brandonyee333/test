USE hive.default;

DELETE FROM Form;

INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now()), 'test', 1);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '1' DAY), 'test', 4);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '2' DAY), 'test', 2);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '6' DAY), 'test', 2);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '8' DAY), 'test', 7);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '9' DAY), 'test', 1);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '12' DAY), 'test', 4);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '14' DAY), 'test', 2);
INSERT INTO Form(assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '15' DAY), 'test', 6);
