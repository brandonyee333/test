INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Custom', 10, 'http://liferay.com', 1, timestamp '2022-09-20T15:25:51.433Z', 'assetClicked', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Blog', 20, 'http://liferay.com', 1, timestamp '2022-09-21T15:25:51.433Z', 'blogClicked', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 30, 'http://liferay.com', 1, timestamp '2022-09-21T15:25:51.433Z', 'fieldBlurred', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 40, 'http://liferay.com', 1, timestamp '2022-09-20T15:25:51.433Z', 'formViewed', '3', 'Liferay', '2');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Page', 50, 'http://liferay.com', 1, timestamp '2022-09-20T15:25:51.433Z', 'pageUnloaded', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('SocialBookmarks', 60, 'http://liferay.com', 1, timestamp '2022-09-20T15:25:51.433Z', 'shared', '1', 'Liferay', '1');

INSERT INTO BQIdentity (individualId, userId) VALUES ('1', '1');

INSERT INTO BQIndividual (id) VALUES ('1');

INSERT INTO BQSession (channelId, id, sessionEnd, sessionStart, userId) VALUES (1, '1', timestamp '2022-09-20T15:25:51.433Z', timestamp '2022-09-20T15:55:51.433Z', '1');
INSERT INTO BQSession (channelId, id, sessionEnd, sessionStart, userId) VALUES (1, '3', timestamp '2022-09-21T15:25:51.433Z', timestamp '2022-09-21T15:45:51.433Z', '2');