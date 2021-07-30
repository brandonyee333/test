INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (1, 1, timestamp '${now-1h}', (SELECT id FROM EventDefinition WHERE name = 'assetClicked'), 1, '1');
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, individualId, sessionId) VALUES (2, 1, timestamp '${now-1h}', (SELECT id FROM EventDefinition WHERE name = 'assetClicked'), 1, '1');

INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id FROM EventAttributeDefinition WHERE name = 'canonicalUrl'), 1, 'http://liferay.com');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id FROM EventAttributeDefinition WHERE name = 'pageTitle'), 1, 'Liferay');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id FROM EventAttributeDefinition WHERE name = 'canonicalUrl'), 2, 'http://liferay.com');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventId, value) VALUES ((SELECT id FROM EventAttributeDefinition WHERE name = 'pageTitle'), 2, 'Liferay');