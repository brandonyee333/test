INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Test Custom Event Definition', 'Test Event Definition', 3002, 'test', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Event Attribute Definition Description', 'Event Attribute Definition Name', 3001, 'test', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(3001, 3002, 'Test');

INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2001, 1, timestamp '${now-18d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '1');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2002, 1, timestamp '${now-17d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '2');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2003, 1, timestamp '${now-16d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '3');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2004, 1, timestamp '${now-15d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '4');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2005, 1, timestamp '${now-14d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '5');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2006, 1, timestamp '${now-13d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '6');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2007, 1, timestamp '${now-12d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '7');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2008, 1, timestamp '${now-11d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '8');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2009, 1, timestamp '${now-10d}', (SELECT id FROM EventDefinition WHERE name = 'test'), 1, '9');

INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-18d}', 2001, 'Event Attribute Value 1');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-17d}', 2002, 'Event Attribute Value 2');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-16d}', 2003, 'Event Attribute Value 3');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-15d}', 2004, 'Event Attribute Value 4');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-14d}', 2005, 'EvEnT AtTrIbUtE Value 1');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-13d}', 2006, 'EvEnT AtTrIbUtE Value 2');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-12d}', 2007, 'EvEnT AtTrIbUtE Value 3');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-11d}', 2008, 'EvEnT AtTrIbUtE Value 4');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(3001, timestamp '${now-10d}', 2009, 'A totally different value');
