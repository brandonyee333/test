INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1000, true, now(), 'http://test.com');

INSERT INTO EventDefinition(id, hidden, name, type) VALUES (1000, false, 'customViewEventToUnhide', 'CUSTOM');