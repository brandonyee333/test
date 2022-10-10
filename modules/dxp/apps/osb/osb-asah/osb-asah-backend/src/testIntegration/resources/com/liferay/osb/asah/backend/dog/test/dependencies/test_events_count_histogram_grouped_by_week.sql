INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${now-1d}', 'assetClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-18d}', 'blogClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-9d}', 'blogClicked', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-9d}', 'blogClicked', '1', '1');

INSERT INTO BQIdentity (individualId, userId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');