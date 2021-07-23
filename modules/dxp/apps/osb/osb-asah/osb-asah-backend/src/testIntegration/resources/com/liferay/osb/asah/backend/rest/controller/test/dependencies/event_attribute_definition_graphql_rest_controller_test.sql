INSERT INTO Channel (createDate, name) VALUES ('2021-03-03 18:12:00-07', 'testChannelName');

INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('NUMBER', 'Item Price', 'itemPrice', 'LOCAL');

INSERT INTO EventDefinition (displayName, name, type) VALUES ('Added To Cart', 'addedToCart', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId, sampleValue) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from EventDefinition WHERE name='addedToCart'), '150.00');

INSERT INTO Event (analyticsEventId, applicationId, channelId, createDate, dataSourceId, eventDate, eventDefinitionId, sessionId, userId) VALUES ('analyticsEventId1', 'Custom', (SELECT id from Channel WHERE name='testChannelName'), '2021-03-03 18:12:00-07', 1, '2021-03-03 18:12:00-00', (SELECT id from EventDefinition WHERE name='addedToCart'), 6789, 'userId1');
INSERT INTO Event (analyticsEventId, applicationId, channelId, createDate, dataSourceId, eventDate, eventDefinitionId, sessionId, userId) VALUES ('analyticsEventId2', 'Custom', (SELECT id from Channel WHERE name='testChannelName'), '2021-03-03 18:12:12-07', 1, '2021-03-03 18:12:12-00', (SELECT id from EventDefinition WHERE name='addedToCart'), 6790, 'userId2');

INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from Event WHERE analyticsEventId='analyticsEventId1'), '150.00');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='itemPrice'), (SELECT id from Event WHERE analyticsEventId='analyticsEventId2'), '275.00');