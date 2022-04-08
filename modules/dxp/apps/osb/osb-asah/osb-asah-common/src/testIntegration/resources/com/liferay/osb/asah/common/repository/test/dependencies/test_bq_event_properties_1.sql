INSERT INTO EventDefinition(description, displayName, id, name, type) VALUES('Added item to cart', 'Added to Cart', 2002, 'addedToCart', 'CUSTOM');

INSERT INTO EventAttributeDefinition(dataType, description, displayName, id, name, type) VALUES('STRING', 'Item Name', 'Item Name', 2001, 'itemName', 'LOCAL');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES(2001, 2002, 'Toy Robot');

INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1001, 1, timestamp '${now-18d}', 'addedToCart', '1', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1002, 1, timestamp '${now-17d}', 'addedToCart', '2', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1003, 1, timestamp '${now-16d}', 'addedToCart', '3', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1004, 1, timestamp '${now-15d}', 'addedToCart', '4', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1005, 1, timestamp '${now-14d}', 'addedToCart', '5', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1006, 1, timestamp '${now-13d}', 'addedToCart', '6', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1007, 1, timestamp '${now-12d}', 'addedToCart', '7', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1008, 1, timestamp '${now-11d}', 'addedToCart', '8', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1009, 1, timestamp '${now-10d}', 'addedToCart', '9', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1010, 1, timestamp '${now-9d}', 'addedToCart', '10', '1');
INSERT INTO BQEvent (id, channelId, eventDate, eventId, sessionId, userId) VALUES (1011, 1, timestamp '${now-8d}', 'addedToCart', '11', '1');

INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-18d}', 1001, 'itemName', 'Toy Robot');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-17d}', 1002, 'itemName', 'Cellular Phone');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-16d}', 1003, 'itemName', 'Cabinet');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-15d}', 1004, 'itemName', 'Dumbbells');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-14d}', 1005, 'itemName', 'Books');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-13d}', 1006, 'itemName', 'Hair Dye');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-12d}', 1007, 'itemName', 'Apples');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-11d}', 1008, 'itemName', 'Apples');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-10d}', 1009, 'itemName', 'Plates');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-9d}', 1010, 'itemName', 'Wheels');
INSERT INTO BQEventProperty(eventDate, id, name, value) VALUES(timestamp '${now-8d}', 1011, 'itemName', 'Windshield Wipers');