INSERT INTO Channel (id, createDate) VALUES (1, '2021-05-31');
INSERT INTO Channel (id, createDate) VALUES (2, '2021-06-01');

INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('STRING', 'Test Attribute 1', 12345, 'testAttribute1', 'LOCAL');
INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('NUMBER', 'Test Attribute 2', 23456, 'testAttribute2', 'LOCAL');

INSERT INTO EventDefinition (applicationId, id, blocked, description, displayName, name, type) VALUES ('CustomEvent', 246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');
INSERT INTO EventDefinition (applicationId, id, blocked, description, displayName, name, type) VALUES ('CustomEvent', 135791, false, 'Test Description 2', 'Test Display Name 2', 'testEvent2', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (12345, 246810);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (23456, 246810);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (12345, 135791);
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES (23456, 135791);