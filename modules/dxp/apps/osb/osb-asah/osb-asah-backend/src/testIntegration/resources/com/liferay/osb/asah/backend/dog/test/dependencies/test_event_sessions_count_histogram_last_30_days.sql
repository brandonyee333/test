INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-2d}', 'assetClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-2d}', 'blogClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-20d}', 'assetDownloaded', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-20d}', 'assetDownloaded', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-20d}', 'assetDownloaded', '3', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-35d}', 'assetClicked', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-10d}', 'blogClicked', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-10d}', 'blogClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-10d}', 'blogClicked', '1', '1');

INSERT INTO BQIdentity (id, individualId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');