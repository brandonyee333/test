INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-2d}', 'assetClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-25d}', 'blogClicked', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Form', 1, timestamp '${today-20d}', 'fieldBlurred', '1', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${today-35d}', 'assetClicked', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-10d}', 'blogClicked', '2', '1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${today-10d}', 'blogClicked', '1', '1');

INSERT INTO BQIdentity (individualId, userId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');