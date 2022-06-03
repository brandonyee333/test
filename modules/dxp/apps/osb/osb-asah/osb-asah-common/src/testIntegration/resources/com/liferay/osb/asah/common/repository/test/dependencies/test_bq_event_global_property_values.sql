INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');

INSERT INTO BQEvent (channelId, eventDate, eventId, id, title, userId) VALUES (1, '2021-05-14', 'testEvent1', 1, 'test', '9');