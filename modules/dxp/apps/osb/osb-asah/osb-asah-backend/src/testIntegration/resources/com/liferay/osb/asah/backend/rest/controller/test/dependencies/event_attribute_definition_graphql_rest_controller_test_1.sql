INSERT INTO Channel (createDate, id, name) VALUES ('2021-03-03 18:12:00-07', 10001, 'testChannelName');

INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('NUMBER', 'Item Price', 'itemPrice', 'LOCAL');

INSERT INTO EventDefinition (displayName, name, type) VALUES ('Added To Cart', 'addedToCart', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from EventDefinition WHERE name='addedToCart'), '150.00');