INSERT INTO Channel(id, createDate, name) VALUES(2468, '2021-05-12 18:12:00', 'testChannelName');

INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 01:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 03:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 10:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 11:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 13:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 15:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 19:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-12 20:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-13 04:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-13 08:00:00', 12345, 'http://localhost:8080/web/guest/home');
INSERT INTO Event(applicationId, canonicalUrl, channelId, eventDate, eventDefinitionId, url) VALUES('Page', 'http://localhost:8080', 2468, '2021-05-13 18:00:00', 12345, 'http://localhost:8080/web/guest/home');

INSERT INTO EventDefinition(id, blocked, description, displayName, name, type) VALUES(12345, false, 'Test Event Description', 'Test Display Name', 'testName', 'STRING');