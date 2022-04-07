INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Test Custom Event Definition', 'Test Event Definition', 3002, 'test', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Event Attribute Definition Description', 'Event Attribute Definition Name', 3001, 'test', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(3001, 3002, 'Test');

INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2001, 1, timestamp '${now-18d}', 'test', '1', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2002, 1, timestamp '${now-17d}', 'test', '2', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2003, 1, timestamp '${now-16d}', 'test', '3', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2004, 1, timestamp '${now-15d}', 'test', '4', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2005, 1, timestamp '${now-14d}', 'test', '5', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2006, 1, timestamp '${now-13d}', 'test', '6', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2007, 1, timestamp '${now-12d}', 'test', '7', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2008, 1, timestamp '${now-11d}', 'test', '8', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (2009, 1, timestamp '${now-10d}', 'test', '9', '1');

INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-18d}', 2001, 'test', 'Event Attribute Value 1');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-17d}', 2002, 'test', 'Event Attribute Value 2');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-16d}', 2003, 'test', 'Event Attribute Value 3');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-15d}', 2004, 'test', 'Event Attribute Value 4');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-14d}', 2005, 'test', 'EvEnT AtTrIbUtE Value 1');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-13d}', 2006, 'test', 'EvEnT AtTrIbUtE Value 2');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-12d}', 2007, 'test', 'EvEnT AtTrIbUtE Value 3');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-11d}', 2008, 'test', 'EvEnT AtTrIbUtE Value 4');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES(timestamp '${now-10d}', 2009, 'test', 'A totally different value');
