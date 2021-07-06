INSERT INTO EventDefinition(id, hidden, name, type) VALUES (1000, false, 'customViewEventToHide', 'CUSTOM');

INSERT INTO BlockedEventDefinition(eventDefinitionId, hidden, lastSeenDate, lastSeenURL) VALUES (1000, false, now(), 'http://test.com');