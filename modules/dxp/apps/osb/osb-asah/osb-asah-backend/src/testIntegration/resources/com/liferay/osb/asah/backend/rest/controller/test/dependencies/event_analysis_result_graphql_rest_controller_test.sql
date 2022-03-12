INSERT INTO Channel (id, createDate, name) VALUES (2468, '2021-05-12 18:12:00', 'testChannelName');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (12345, false, 'Test Event Description', 'Test Display Name', 'testName', 'CUSTOM');

INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 01:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 03:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 10:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 11:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 13:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 15:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 16:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-12 16:30:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-13 04:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-13 08:00:00', 'testName');
INSERT INTO BQEvent (channelId, eventDate, eventId) VALUES (2468, '2021-05-13 18:00:00', 'testName');