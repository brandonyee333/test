INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '1', '10', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '2', '10', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '3', '30', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '4', '40', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '5', '50', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '6', '60', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '7', '70', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today}', '8', '80', 'test');

INSERT INTO Individual (id, modifiedDate) VALUES ('10', timestamp '${now}');
INSERT INTO Individual (id, modifiedDate) VALUES ('30', timestamp '${now}');
INSERT INTO Individual (id, modifiedDate) VALUES ('50', timestamp '${now}');
INSERT INTO Individual (id, modifiedDate) VALUES ('70', timestamp '${now}');
INSERT INTO Individual (id, modifiedDate) VALUES ('80', timestamp '${now}');

INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '1', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '2', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '3', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '4', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '5', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '6', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '7', 1);
INSERT INTO JournalDaily (assetId, channelId, eventDate, userId, views) VALUES ('1', 1, timestamp '${now-1d}', '8', 1);