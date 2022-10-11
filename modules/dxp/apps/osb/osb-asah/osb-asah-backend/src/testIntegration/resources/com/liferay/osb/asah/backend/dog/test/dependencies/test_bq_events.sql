INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Custom', 10, 'http://liferay.com', 1, timestamp '${now-3d}', 'assetClicked', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Blog', 20, 'http://liferay.com', 1, timestamp '${now-6d}', 'blogClicked', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 30, 'http://liferay.com', 1, timestamp '${now-7d}', 'fieldBlurred', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 40, 'http://liferay.com', 1, timestamp '${now-10d}', 'formViewed', '3', 'Liferay', '2');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Page', 50, 'http://liferay.com', 1, timestamp '${now-26d}', 'pageUnloaded', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('SocialBookmarks', 60, 'http://liferay.com', 1, timestamp '${now-34d}', 'shared', '1', 'Liferay', '1');

INSERT INTO BQIdentity (id, individualId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');

INSERT INTO BQSession (channelId, id, sessionEnd, sessionStart, userId) VALUES (1, '1', timestamp '${now-3d}', timestamp '${now-3d}', '1');
INSERT INTO BQSession (channelId, id, sessionEnd, sessionStart, userId) VALUES (1, '2', timestamp '${now-34d}', timestamp '${now-34d}', '1');
INSERT INTO BQSession (channelId, id, sessionEnd, sessionStart, userId) VALUES (1, '3', timestamp '${now-10d}', timestamp '${now-10d}', '2');