INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${now-1h}', 'assetClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${now-1h}', 'assetClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-3h}', 'blogClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Form', 1, timestamp '${now-3h}', 'fieldBlurred', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${now-3h}', 'assetClicked', '2', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-10h}', 'blogClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-10h}', 'blogClicked', '2', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-10h}', 'blogClicked', '2', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-10h}', 'blogClicked', '3', '1');

INSERT INTO Identity_Raw (id, individualId) VALUES ('1', '1');

INSERT INTO Individual (id) VALUES ('1');