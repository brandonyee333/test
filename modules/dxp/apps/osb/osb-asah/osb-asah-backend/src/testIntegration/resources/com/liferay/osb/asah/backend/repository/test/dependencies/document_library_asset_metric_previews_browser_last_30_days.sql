USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Firefox', 1, date_trunc('HOUR', timestamp '${now-1d}'), 'test', 5);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Chrome', 1, date_trunc('HOUR', timestamp '${now-5d}'), 'test', 2);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Firefox',  1, date_trunc('HOUR', timestamp '${now-10d}'), 'test', 3);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Chrome', 1, date_trunc('HOUR', timestamp '${now-15d}'), 'test', 4);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Firefox', 1, date_trunc('HOUR', timestamp '${now-25h}'), 'test', 5);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Chrome', 1, date_trunc('HOUR', timestamp '${now-35d}'), 'test', 6);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Firefox', 1, date_trunc('HOUR', timestamp '${now-12d}'), 'test', 1);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Chrome', 1, date_trunc('HOUR', timestamp '${now-4d}'), 'test', 3);
INSERT INTO DocumentLibrary (assetId, browserName, channelId, eventDate, projectId, previews) VALUES ('e131fabc', 'Opera Desktop', 1, date_trunc('HOUR', timestamp '${now-18d}'), 'test', 3);