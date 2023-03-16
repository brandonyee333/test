INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Document', 'e131fabc', 'assetTitle', 1, 'https://localhost:8080/page1', DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), 'documentPreviewed', '0', 'page1', 'u1');
INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Ratings', 'e131fabc', 'assetTitle', 1, 'https://localhost:8080/page1', DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), 'VOTE', '1', 'page1', 'u1');
INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Document', 'e131fabc', 'assetTitle', 2, 'https://localhost:8080/page2', DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), 'documentPreviewed', '2', 'page1', 'u1');
INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Ratings', 'e131fabc', 'assetTitle', 2, 'https://localhost:8080/page2', DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), 'VOTE', '3', 'page1', 'u1');
INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Document', 'e131fabc', 'assetTitle', 1, 'https://localhost:8080/page2', DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), 'documentPreviewed', '4', 'page1', 'u2');
INSERT INTO Event (applicationId, assetId, assetTitle, channelId, canonicalUrl, eventDate, eventId, id, title, userId) VALUES ('Ratings', 'e131fabc', 'assetTitle', 1, 'https://localhost:8080/page2', DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), 'VOTE', '5', 'page1', 'u2');

INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), '1', 'classPK', '01234');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), '1', 'className', 'com.liferay.document.library.kernel.model.DLFileEntry');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), '1', 'ratingType', 'stars');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now}', HOUR), '1', 'score', '1.0');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (2, DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), '3', 'classPK', '01235');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (2, DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), '3', 'className', 'com.liferay.document.library.kernel.model.DLFileEntry');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (2, DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), '3', 'ratingType', 'stars');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (2, DATETIME_TRUNC(TIMESTAMP '${now-2h}', HOUR), '3', 'score', '0.4');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), '5', 'classPK', '01236');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), '5', 'className', 'com.liferay.document.library.kernel.model.DLFileEntry');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), '5', 'ratingType', 'stars');
INSERT INTO EventProperty (channelId, eventDate, id, name, value) VALUES (1, DATETIME_TRUNC(TIMESTAMP '${now-7h}', HOUR), '5', 'score', '0.8');