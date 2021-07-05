INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1001, true, now(), 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1002, true, now(), 'http://test.com');
INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1003, true, now(), 'http://test.com');

INSERT INTO EventDefinition(id, blocked, hidden, name, type) VALUES (1001, true, true, 'CustomEventNotification1', 'CUSTOM');
INSERT INTO EventDefinition(id, blocked, hidden, name, type) VALUES (1002, true, true, 'CustomEventNotification2', 'CUSTOM');
INSERT INTO EventDefinition(id, blocked, hidden, name, type) VALUES (1003, true, true, 'CustomEventNotification3', 'CUSTOM');