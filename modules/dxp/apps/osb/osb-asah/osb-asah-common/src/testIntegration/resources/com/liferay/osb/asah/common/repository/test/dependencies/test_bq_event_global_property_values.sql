INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');

INSERT INTO EventDefinition (applicationId, id, blocked, description, displayName, name, type) VALUES ('CustomEvent', 246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');

INSERT INTO BQEvent (applicationId, channelId, eventDate, eventId, id, title, userId) VALUES ('CustomEvent', 1, '2021-05-14', 'testEvent1', 1, 'test', '9');