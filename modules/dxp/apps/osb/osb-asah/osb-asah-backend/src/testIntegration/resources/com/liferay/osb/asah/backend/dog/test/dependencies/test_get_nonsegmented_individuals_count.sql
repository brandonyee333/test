INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '1', '10', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '2', '10', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '3', '30', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '4', '40', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '5', '50', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '6', '60', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '7', '70', 'test');
INSERT INTO Identity_Raw (createDate, id, individualId, projectId) VALUES (timestamp '${today-1d}', '8', '80', 'test');

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

INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (1, timestamp '${today}', '2', '10', timestamp '${now}', 1001, 'ACTIVE');
INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (1, timestamp '${today}', '3', '30', timestamp '${now}', 1002, 'ACTIVE');
INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (1, timestamp '${today}', '4', null, timestamp '${now}', 1001, 'ACTIVE');
INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (2, timestamp '${today}', '5', '50', timestamp '${now}', 1004, 'ACTIVE');
INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (2, timestamp '${today}', '6', '70', timestamp '${now}', 1004, 'ACTIVE');
INSERT INTO Membership (channelId, createDate, identityId, individualId, modifiedDate, segmentId, status) VALUES (1, timestamp '${today}', '8', '80', timestamp '${now}', 1003, 'ACTIVE');