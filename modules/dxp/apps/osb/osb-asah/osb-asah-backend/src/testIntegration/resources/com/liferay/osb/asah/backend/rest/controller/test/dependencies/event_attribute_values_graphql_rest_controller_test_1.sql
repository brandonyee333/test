INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Event Attribute Definition Description', 'Event Attribute Definition Name', 3001, 'test', 'LOCAL');

INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Test Custom Event Definition', 'Test Event Definition', 3002, 'test', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(3001, 3002, 'Test');