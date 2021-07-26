USE hive.default;

DELETE FROM DocumentLibrary;

INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-1d}'), 1, false, 1, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-5d}'), 1, false, 2, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-10d}'), 2, true, 3, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-15d}'), 2, true, 4, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-25h}'), 3, true, 5, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 4, 6, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-29d}'), 5, 0, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-31d}'), 1, true, 7, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, knownIndividual, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-40d}'), 1, false, 8, 'test');
INSERT INTO DocumentLibrary (assetId, channelId, eventDate, individualId, previews, projectId) VALUES ('e131fabc', 1, date_trunc('HOUR', timestamp '${now-45d}'), 1, 9, 'test');