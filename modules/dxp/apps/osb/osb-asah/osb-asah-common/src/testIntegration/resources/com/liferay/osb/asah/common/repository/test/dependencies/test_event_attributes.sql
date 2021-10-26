INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Added item to cart', 'Added to Cart', 2002, 'addedToCart', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Item Name', 'Item Name', 2001, 'itemName', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(2001, 2002, 'Toy Robot');

INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1001, 1, date_trunc('DAY', timestamp '${now-8d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '1');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1002, 1, date_trunc('DAY', timestamp '${now-8d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '2');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1003, 1, date_trunc('DAY', timestamp '${now-7d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '3');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1004, 1, date_trunc('DAY', timestamp '${now-7d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '4');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1005, 1, date_trunc('DAY', timestamp '${now-6d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '5');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1006, 1, date_trunc('DAY', timestamp '${now-6d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '6');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1007, 1, date_trunc('DAY', timestamp '${now-5d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '7');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1008, 1, date_trunc('DAY', timestamp '${now-4d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '8');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1009, 1, date_trunc('DAY', timestamp '${now-3d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '9');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1010, 1, date_trunc('DAY', timestamp '${now-2d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '10');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1011, 1, date_trunc('DAY', timestamp '${now-1d}'), (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '11');

INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-8d}'), 1001, 'Toy Robot');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-8d}'), 1002, 'Cellular Phone');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-7d}'), 1003, 'Cabinet');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-7d}'), 1004, 'Dumbbells');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-6d}'), 1005, 'Books');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-6d}'), 1006, 'Hair Dye');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-5d}'), 1007, 'Apples');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-4d}'), 1008, 'Apples');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-3d}'), 1009, 'Plates');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-2d}'), 1010, 'Wheels');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, date_trunc('DAY', timestamp '${now-1d}'), 1011, 'Windshield Wipers');