INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Custom', 10, 'http://liferay.com', 1, timestamp '${now-3h}', 'assetClicked', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Blog', 20, 'http://liferay.com', 1, timestamp '${now-1h}', 'blogClicked', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 30, 'http://liferay.com', 1, timestamp '${now-5h}', 'fieldBlurred', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 40, 'http://liferay.com', 1, timestamp '${now-2h}', 'formViewed', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Page', 50, 'http://liferay.com', 1, timestamp '${now-26h}', 'pageUnloaded', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('SocialBookmarks', 60, 'http://liferay.com', 1, timestamp '${now-30h}', 'shared', '1', 'Liferay', '1');

INSERT INTO BQIdentity (id, individualId) VALUES ('1', '1');