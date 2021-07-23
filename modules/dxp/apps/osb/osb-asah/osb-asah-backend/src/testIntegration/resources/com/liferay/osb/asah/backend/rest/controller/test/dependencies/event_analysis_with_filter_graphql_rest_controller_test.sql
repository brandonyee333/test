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

INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-14', 246810, 1, 9, 6701);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-15', 135791, 2, 13, 6702);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-15', 246810, 3, 1, 6702);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-16', 246810, 4, 8, 6703);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-17', 246810, 5, 2, 6704);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-17', 246810, 6, 4, 6704);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-17', 246810, 7, 5, 6704);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-18', 246810, 8, 7, 6705);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-19', 246810, 9, 8, 6706);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-20', 246810, 10, 11, 6707);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-21', 246810, 11, 8, 6708);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-21', 246810, 12, 8, 6708);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-22', 246810, 13, 9, 6709);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-22', 246810, 14, 11, 6709);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-23', 246810, 15, 1, 6710);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-24', 246810, 16, 11, 6711);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-27', 246810, 17, 9, 6712);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-27', 246810, 18, 12, 6712);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-28', 246810, 19, 12, 6713);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-05-30', 246810, 20, 14, 6714);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-06-01', 246810, 21, 14, 6715);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-06-01', 246810, 22, 14, 6715);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (1, '2021-06-02', 246810, 23, 13, 6716);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-19', 246810, 24, 1, 6717);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-22', 246810, 25, 2, 6718);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-25', 246810, 26, 3, 6719);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-27', 246810, 27, 3, 6720);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-30', 246810, 28, 5, 6721);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-05-31', 246810, 29, 6, 6722);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-06-01', 246810, 30, 8, 6723);
INSERT INTO Event (channelId, eventDate, eventDefinitionId, id, individualId, sessionId) VALUES (2, '2021-06-02', 246810, 31, 10, 6724);

INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (12345, '2021-05-17', 6, 1004, 'This should also match test');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (12345, '2021-05-20', 10, 1003, 'This is a test');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (12345, '2021-05-22', 14, 1005, 'This should match test');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (23456, '2021-05-17', 6, 1002, '350');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (23456, '2021-05-20', 10, 1001, '275');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES (23456, '2021-05-22', 14, 1000, '201');