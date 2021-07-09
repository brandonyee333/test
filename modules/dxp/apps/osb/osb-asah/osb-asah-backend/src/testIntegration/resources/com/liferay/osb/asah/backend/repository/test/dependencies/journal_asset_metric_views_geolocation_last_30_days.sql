USE hive.default;

DELETE FROM Journal;

INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', now() - INTERVAL '1' DAY), 'test', 1);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', now() - INTERVAL '5' DAY), 'test', 2);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', now() - INTERVAL '10' DAY), 'test', 3);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', now() - INTERVAL '15' DAY), 'test', 4);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', now() - INTERVAL '20' HOUR), 'test', 5);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', now() - INTERVAL '29' DAY), 'test', 6);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', now() - INTERVAL '31' DAY), 'test', 7);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', now() - INTERVAL '40' DAY), 'test', 8);
INSERT INTO Journal(assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', now() - INTERVAL '45' DAY), 'test', 9);