INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Custom', 10, 'http://liferay.com', 1, timestamp '${now-3d}', 'assetClicked', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Blog', 20, 'http://liferay.com', 1, timestamp '${now-6d}', 'blogClicked', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 30, 'http://liferay.com', 1, timestamp '${now-7d}', 'fieldBlurred', '2', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Form', 40, 'http://liferay.com', 1, timestamp '${now-10d}', 'formViewed', '3', 'Liferay', '2');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('Page', 50, 'http://liferay.com', 1, timestamp '${now-26d}', 'pageUnloaded', '1', 'Liferay', '1');
INSERT INTO BQEvent (applicationId, id, canonicalUrl, channelId, eventDate, eventId, sessionId, title, userId) VALUES ('SocialBookmarks', 60, 'http://liferay.com', 1, timestamp '${now-34d}', 'shared', '1', 'Liferay', '1');

INSERT INTO BQIdentity (emailAddressHashed, id, userId) VALUES (1, 1, '1');

INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 86400000, '1', timestamp '${now-2d}', timestamp '${now-1d}');
INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 172800000, '2', timestamp '${now-15d}', timestamp '${now-13d}');
INSERT INTO BQSession (channelId, duration, id, sessionStart, sessionEnd) VALUES (1, 345600000, '3', timestamp '${now-65d}', timestamp '${now-61d}');

