INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES(true, null, null, 'subscribed', 'CUSTOM');

INSERT INTO BlockedEventDefinition(eventDefinitionId, lastSeenDate, lastSeenUrl) VALUES((SELECT id FROM EventDefinition WHERE name='subscribed'), '2020-12-27 03:03:00-07', 'http://localhost:8080');