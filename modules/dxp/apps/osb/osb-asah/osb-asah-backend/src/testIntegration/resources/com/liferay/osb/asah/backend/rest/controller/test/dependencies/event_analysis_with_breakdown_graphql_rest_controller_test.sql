INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');
INSERT INTO Channel (id, createDate) VALUES (2, '2021-06-01');

INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('STRING', 'Test Attribute 1', 12345, 'testAttribute1', 'LOCAL');
INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('NUMBER', 'Test Attribute 2', 23456, 'testAttribute2', 'LOCAL');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');
INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (135791, false, 'Test Description 2', 'Test Display Name 2', 'testEvent2', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (12345, 246810);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (23456, 246810);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (12345, 135791);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (23456, 135791);

INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-14', 'testEvent1', 1, '9');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-15', 'testEvent2', 2, '13');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-15', 'testEvent1', 3, '1');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-16', 'testEvent1', 4, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-17', 'testEvent1', 5, '2');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-17', 'testEvent1', 6, '4');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-17', 'testEvent1', 7, '5');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-18', 'testEvent1', 8, '7');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-19', 'testEvent1', 9, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-20', 'testEvent1', 10, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-21', 'testEvent1', 11, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-21', 'testEvent1', 12, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-22', 'testEvent1', 13, '9');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-22', 'testEvent1', 14, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-23', 'testEvent1', 15, '1');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-24', 'testEvent1', 16, '11');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-27', 'testEvent1', 17, '9');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-27', 'testEvent1', 18, '12');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-28', 'testEvent1', 19, '12');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-05-30', 'testEvent1', 20, '14');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-06-01', 'testEvent1', 21, '14');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-06-01', 'testEvent1', 22, '14');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (1, '2021-06-02', 'testEvent1', 23, '13');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-19', 'testEvent1', 24, '1');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-22', 'testEvent1', 25, '2');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-25', 'testEvent1', 26, '3');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-27', 'testEvent1', 27, '3');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-30', 'testEvent1', 28, '5');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-05-31', 'testEvent1', 29, '6');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-06-01', 'testEvent1', 30, '8');
INSERT INTO BQEvent (channelId, eventDate, eventId, id, userId) VALUES (2, '2021-06-02', 'testEvent1', 31, '10');

INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-17', 6, 'testAttribute1', 'This should also match test');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-20', 10, 'testAttribute1', 'This is a test');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-22', 14, 'testAttribute1', 'This should match test');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-17', 6, 'testAttribute2', '3500');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-20', 10, 'testAttribute2', '2750');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES ('2021-05-22', 14, 'testAttribute2', '2010');