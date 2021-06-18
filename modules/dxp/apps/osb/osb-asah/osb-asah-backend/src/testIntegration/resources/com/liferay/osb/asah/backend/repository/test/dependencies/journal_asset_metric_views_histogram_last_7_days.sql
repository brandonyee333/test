USE hive.default;

DELETE FROM Journal;

INSERT INTO Journal(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES 
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '28' HOUR), 'test', 2),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '30' HOUR), 'test', 1);