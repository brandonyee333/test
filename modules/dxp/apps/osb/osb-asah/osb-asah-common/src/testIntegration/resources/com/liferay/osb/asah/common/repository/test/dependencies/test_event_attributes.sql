INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Added item to cart', 'Added to Cart', 2002, 'addedToCart', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Item Name', 'Item Name', 2001, 'itemName', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(2001, 2002, 'Toy Robot');

INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1001, 1, timestamp '2021-10-15', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '1');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1002, 1, timestamp '2021-10-16', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '2');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1003, 1, timestamp '2021-10-17', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '3');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1004, 1, timestamp '2021-10-18', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '4');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1005, 1, timestamp '2021-10-19', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '5');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1006, 1, timestamp '2021-10-20', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '6');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1007, 1, timestamp '2021-10-21', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '7');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1008, 1, timestamp '2021-10-22', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '8');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1009, 1, timestamp '2021-10-23', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '9');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1010, 1, timestamp '2021-10-24', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '10');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1011, 1, timestamp '2021-10-25', (SELECT id FROM EventDefinition WHERE name = 'addedToCart'), 1, '11');

INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-15', 1001, 'Toy Robot');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-16', 1002, 'Cellular Phone');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-17', 1003, 'Cabinet');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-18', 1004, 'Dumbbells');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-19', 1005, 'Books');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-20', 1006, 'Hair Dye');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-21', 1007, 'Apples');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-22', 1008, 'Apples');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-23', 1009, 'Plates');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-24', 1010, 'Wheels');
INSERT INTO EventAttribute(eventAttributeDefinitionId, eventDate, eventId, value) VALUES(2001, timestamp '2021-10-25', 1011, 'Windshield Wipers');