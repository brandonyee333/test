INSERT INTO EventAttributeDefinition(dataType, displayName, name) VALUES('NUMBER', 'Item Price', 'itemPrice');
INSERT INTO EventAttributeDefinition(dataType, displayName, name) VALUES('NUMBER', 'Quantity', 'quantity');
INSERT INTO EventAttributeDefinition(dataType, displayName, name) VALUES('STRING', 'Item Name', 'itemName');

INSERT INTO EventDefinition(description, displayName, name, type) VALUES('Added an item to shopping cart', 'Added To Cart', 'addedToCart', 'CUSTOM');
INSERT INTO EventDefinition(description, displayName, name, type) VALUES('Saved item to wish list', 'Save To List', 'savedToList', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from EventDefinition WHERE name='addedToCart'), '1500.00');
INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='quantity'), (SELECT id from EventDefinition WHERE name='addedToCart'), '7');
INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemName'), (SELECT id from EventDefinition WHERE name='addedToCart'), 'laptop');
INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemName'), (SELECT id from EventDefinition WHERE name='savedToList'), 'table');