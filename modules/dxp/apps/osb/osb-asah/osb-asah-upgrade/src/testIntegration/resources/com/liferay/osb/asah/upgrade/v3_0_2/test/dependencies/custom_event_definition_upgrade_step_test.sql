INSERT INTO EventDefinition (displayName, hidden, name, type) VALUES ('vote', true, 'vote', 'DEFAULT');

DELETE FROM EventDefinitionEventAttributeDefinition WHERE eventDefinitionId = (SELECT id FROM EventDefinition WHERE name = 'VOTE');

DELETE FROM EventDefinition WHERE name = 'VOTE';