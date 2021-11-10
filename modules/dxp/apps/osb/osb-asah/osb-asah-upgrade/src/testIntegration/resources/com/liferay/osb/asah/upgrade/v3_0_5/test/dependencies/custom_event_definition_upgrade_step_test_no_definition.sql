DELETE FROM EventDefinitionEventAttributeDefinition WHERE eventDefinitionId = (SELECT id FROM EventDefinition WHERE name = 'VOTE');

DELETE FROM EventDefinition WHERE name = 'VOTE';