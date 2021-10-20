USE hive.default;

DELETE FROM Form;

INSERT INTO Form (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${today-4h}'), 'test', 2);
INSERT INTO Form (assetId, channelId, eventDate, projectId, views) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${today-6h}'), 'test', 1)