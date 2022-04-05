INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');

INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('DATE', 'TEST_DATE', 56789, 'testDate', 'LOCAL');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');

INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-14', 'testEvent1', 1, '9');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-17', 'testEvent1', 6, '4');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-17', 'testEvent1', 7, '5');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-18', 'testEvent1', 8, '7');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-19', 'testEvent1', 9, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-20', 'testEvent1', 10, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-21', 'testEvent1', 11, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-21', 'testEvent1', 12, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-22', 'testEvent1', 13, '9');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-22', 'testEvent1', 14, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-23', 'testEvent1', 15, '1');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-24', 'testEvent1', 16, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-13', 'testEvent1', 32, '1');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-13', 'testEvent1', 33, '1');

INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 1, 'testDate', '2021-05-13 10:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 6, 'testDate', '2020-05-13 11:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 7, 'testDate', 'error');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 8, 'testDate', '2021-05-01 01:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 9, 'testDate', '2021-05-13 01:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 10, 'testDate', '2019-05-10 01:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 11, 'testDate', '2021-05-10 02:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 12, 'testDate', '2021-05-10 02:00:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 13, 'testDate', '2021-05-13 01:30:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 14, 'testDate', '2021-05-13 03:30:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 15, 'testDate', '2021-05-13 05:45:12');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 16, 'testDate', '2021-01-13 03:30:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 32, 'testDate', '2021-01-14 03:30:00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-13 10:00:00', 33, 'testDate', '2021-05-13 05:30:00');