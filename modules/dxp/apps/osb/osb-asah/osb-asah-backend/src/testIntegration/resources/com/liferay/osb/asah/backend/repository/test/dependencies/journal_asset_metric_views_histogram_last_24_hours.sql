USE hive.default;

DELETE FROM Journal;

INSERT INTO Journal(assetPrimaryKey, channelId, eventDate, projectId, views) VALUES 
	('e131fabc', 1, date_trunc('HOUR', now()), 'test', 1),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '10' HOUR), 'test', 4),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '7' HOUR), 'test', 2),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '10' HOUR), 'test_2', 2),
	('e131fabc', 2, date_trunc('HOUR', now() - INTERVAL '8' HOUR), 'test', 7),
	('e131fabc', 2, date_trunc('HOUR', now() - INTERVAL '8' HOUR), 'test', 1),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '25' HOUR), 'test', 4),
	('e131fabc', 1, date_trunc('HOUR', now() - INTERVAL '26' HOUR), 'test', 2),
	('e231fabc', 1, date_trunc('HOUR', now() - INTERVAL '5' HOUR), 'test',  4),
	('e231fabc', 1, date_trunc('HOUR', now() - INTERVAL '6' HOUR), 'test', 2),
	('e231fabc', 2, date_trunc('HOUR', now() - INTERVAL '5' HOUR), 'test', 4),
	('e231fabc', 1, date_trunc('HOUR', now() - INTERVAL '25' HOUR), 'test', 2);