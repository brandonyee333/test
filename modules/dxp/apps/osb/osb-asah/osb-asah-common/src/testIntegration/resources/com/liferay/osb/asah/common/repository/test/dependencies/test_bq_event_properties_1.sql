INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Item Name', 'Item Name', 2001, 'itemName', 'LOCAL');

INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Added item to cart', 'Added to Cart', 2002, 'addedToCart', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(2001, 2002, 'Toy Robot');