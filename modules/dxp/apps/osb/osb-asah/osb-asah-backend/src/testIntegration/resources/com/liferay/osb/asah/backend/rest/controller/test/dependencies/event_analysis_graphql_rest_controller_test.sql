INSERT INTO Channel (id, createDate, name) VALUES (2468, '2021-05-12 18:12:00', 'testChannelName');

INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('STRING', 'Test Attribute 1', 12345, 'testAttribute1', 'LOCAL');
INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('NUMBER', 'Test Attribute 2', 23456, 'testAttribute2', 'LOCAL');
INSERT INTO EventAttributeDefinition (dataType, displayName, id, name, type) VALUES ('NUMBER', 'Test Attribute 3', 34567, 'testAttribute3', 'LOCAL');

INSERT INTO EventDefinition (id, blocked, description, displayName, name, type) VALUES (246810, false, 'Test Description 1', 'Test Display Name 1', 'testEvent1', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES (12345, 246810, 'Sample Text');
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES (23456, 246810, 'Sample Number 1');
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES (34567, 246810, 'Sample Number 2');

INSERT INTO EventAnalysis (id, breakdowns, channelId, compareToPrevious, createDate, createdByUserId, createdByUserName, eventAnalysisType, eventDefinitionId, filters, modifiedByUserId, modifiedByUserName, modifiedDate, name, rangeEnd, rangeKey, rangeStart) VALUES (2345, '[{"attributeId": "12345", "attributeType": "EVENT", "binSize": 2000, "dataType": "STRING"}, {"attributeId": "23456", "attributeType": "EVENT", "dataType": "NUMBER"}]', 2468, false, '2021-05-12 18:12:00', '10', 'Test', 'TOTAL', 246810, '[{"attributeId": "34567", "attributeType": "EVENT", "dataType": "NUMBER", "operator": "ge", "values": ["250"]}]', '10', 'Test', '2021-05-12 18:12:00', 'Event Analysis 1', '2021-05-12', null, '2021-05-11');