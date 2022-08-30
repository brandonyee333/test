INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id, sessionId) VALUES ('Custom', 1, timestamp '${now}', 'assetViewed', '1', 'session-1');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id, sessionId) VALUES ('Custom', 1, timestamp '${now-1d}', 'assetViewed', '2', 'session-2');
INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id, sessionId) VALUES ('Custom', 1, timestamp '${now-1d}', 'assetViewed', '3', 'session-3');

INSERT INTO BQEventProperty (channelId, id, name, value) VALUES (1, '1', 'assetId', 'A');
INSERT INTO BQEventProperty (channelId, id, name, value) VALUES (1, '2', 'assetId', 'A');
INSERT INTO BQEventProperty (channelId, id, name, value) VALUES (1, '3', 'assetId', 'A');