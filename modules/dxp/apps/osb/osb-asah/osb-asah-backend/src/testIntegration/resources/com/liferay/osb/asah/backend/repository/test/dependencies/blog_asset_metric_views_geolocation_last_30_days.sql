USE hive.default;

DELETE FROM Blog;

INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', timestamp '${now-1d}'), 'test', 1);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', timestamp '${now-5d}'), 'test', 2);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', timestamp '${now-10d}'), 'test', 3);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', timestamp '${now-15d}'), 'test', 4);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', timestamp '${now-20d}'), 'test', 5);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', timestamp '${now-29d}'), 'test', 6);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'United States', date_trunc('HOUR', timestamp '${now-31d}'), 'test', 7);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'Japan', date_trunc('HOUR', timestamp '${now-40d}'), 'test', 8);
INSERT INTO Blog (assetId, channelId, country, eventDate, projectId, views) VALUES ('e131fabc', 1, 'France', date_trunc('HOUR', timestamp '${now-45d}'), 'test', 9);