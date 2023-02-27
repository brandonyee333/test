INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Custom', 1, timestamp '${now-1d}', 'assetClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-1d}', 'blogClicked', '1', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-1d}', 'blogClicked', '2', '1');
INSERT INTO Event (applicationId, channelId, eventDate, eventId, sessionId, userId) VALUES ('Blog', 1, timestamp '${now-2M}', 'blogClicked', '1', '1');

INSERT INTO Identity_Raw (id, individualId) VALUES ('1', '1');

INSERT INTO Individual (id) VALUES ('1');