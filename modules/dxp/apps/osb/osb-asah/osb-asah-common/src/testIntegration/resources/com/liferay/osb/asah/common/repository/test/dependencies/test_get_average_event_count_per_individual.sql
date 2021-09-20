INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');

INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('DATE', 'TEST_DATE', 56789, 'testDate', 'LOCAL');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');

INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-14', 246810, 1, 9, 6780);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-17', 246810, 6, 4, 6785);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-17', 246810, 7, 5, 6786);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-18', 246810, 8, 7, 6787);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-19', 246810, 9, 8, 6788);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-20', 246810, 10, 11, 6789);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-21', 246810, 11, 11, 6790);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-21', 246810, 12, 8, 6791);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-22', 246810, 13, 9, 6792);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-22', 246810, 14, 11, 6793);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-23', 246810, 15, 1, 6794);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-24', 246810, 16, 11, 6795);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-13', 246810, 32, 1, 6812);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-13', 246810, 33, 1, 6813);

INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 1, '2021-05-13 10:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 6, '2020-05-13 11:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 7, 'error');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 8, '2021-05-01 01:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 9, '2021-05-13 01:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 10, '2019-05-10 01:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 11, '2021-05-10 02:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 12, '2021-05-10 02:00:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 13, '2021-05-13 01:30:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 14, '2021-05-13 03:30:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 15, '2021-05-13 05:45:12');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 16, '2021-01-13 03:30:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 32, '2021-01-14 03:30:00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, value) VALUES (56789, '2021-05-13 10:00:00', 33, '2021-05-13 05:30:00');