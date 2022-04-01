INSERT INTO Channel (createDate, name) VALUES ('2021-03-03 18:12:00-07', 'testChannelName');

INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('NUMBER', 'Item Price', 'itemPrice', 'LOCAL');

INSERT INTO EventDefinition (displayName, name, type) VALUES ('Added To Cart', 'addedToCart', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from EventDefinition WHERE name='addedToCart'), '150.00');

INSERT INTO BQEvent (id, applicationId, channelId, createDate, dataSourceId, eventDate, eventId, sessionId, userId) VALUES ('analyticsEventId1', 'Custom', (SELECT id from Channel WHERE name='testChannelName'), date_trunc('HOUR', timestamp '${now}'), 1, date_trunc('HOUR', timestamp '${now}'), 'addedToCart', 6789, 'userId1');
INSERT INTO BQEvent (id, applicationId, channelId, createDate, dataSourceId, eventDate, eventId, sessionId, userId) VALUES ('analyticsEventId2', 'Custom', (SELECT id from Channel WHERE name='testChannelName'), date_trunc('HOUR', timestamp '${now}'), 1, date_trunc('HOUR', timestamp '${now}'), 'addedToCart', 6790, 'userId2');

INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES (date_trunc('HOUR', timestamp '${now}'), 'analyticsEventId1', 'itemPrice', '150.00');
INSERT INTO BQEventProperty (eventDate, id, name, value) VALUES (date_trunc('HOUR', timestamp '${now}'), 'analyticsEventId2', 'itemPrice', '275.00');