INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Added item to cart', 'Added to Cart', 2002, 'addedToCart', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Item Name', 'Item Name', 2001, 'itemName', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(2001, 2002, 'Toy Robot');

INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1001, 1, date_trunc('DAY', timestamp '${now-8d}'), 'addedToCart', 1, '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1002, 1, date_trunc('DAY', timestamp '${now-8d}'), 'addedToCart', 1, '2');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1003, 1, date_trunc('DAY', timestamp '${now-7d}'), 'addedToCart', 1, '3');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1004, 1, date_trunc('DAY', timestamp '${now-7d}'), 'addedToCart', 1, '4');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1005, 1, date_trunc('DAY', timestamp '${now-6d}'), 'addedToCart', 1, '5');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1006, 1, date_trunc('DAY', timestamp '${now-6d}'), 'addedToCart', 1, '6');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1007, 1, date_trunc('DAY', timestamp '${now-5d}'), 'addedToCart', 1, '7');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1008, 1, date_trunc('DAY', timestamp '${now-4d}'), 'addedToCart', 1, '8');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1009, 1, date_trunc('DAY', timestamp '${now-3d}'), 'addedToCart', 1, '9');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1010, 1, date_trunc('DAY', timestamp '${now-2d}'), 'addedToCart', 1, '10');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, individualId, sessionId) VALUES (1011, 1, date_trunc('DAY', timestamp '${now-1d}'), 'addedToCart', 1, '11');

INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-8d}'), 1001, 'itemName', 'Toy Robot');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-8d}'), 1002, 'itemName', 'Cellular Phone');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-7d}'), 1003, 'itemName', 'Cabinet');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-7d}'), 1004, 'itemName', 'Dumbbells');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-6d}'), 1005, 'itemName', 'Books');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-6d}'), 1006, 'itemName', 'Hair Dye');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-5d}'), 1007, 'itemName', 'Apples');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-4d}'), 1008, 'itemName', 'Apples');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-3d}'), 1009, 'itemName', 'Plates');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-2d}'), 1010, 'itemName', 'Wheels');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(date_trunc('DAY', timestamp '${now-1d}'), 1011, 'itemName', 'Windshield Wipers');