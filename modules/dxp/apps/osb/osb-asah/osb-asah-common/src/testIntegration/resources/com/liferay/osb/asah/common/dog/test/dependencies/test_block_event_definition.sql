INSERT INTO Channel (createDate, name) VALUES ('2021-04-19 00:00:00-07', 'testChannelName');

INSERT INTO EventDefinition (blocked, description, displayName, name, type) VALUES (false, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='subscribed')) ON CONFLICT DO NOTHING;

INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, sessionId) VALUES (1000, (SELECT id from Channel WHERE name='testChannelName'), '2021-04-19 00:00:00-00', (SELECT id from EventDefinition WHERE name='subscribed'), 6789);
INSERT INTO Event (id, channelId, eventDate, eventDefinitionId, sessionId) VALUES (1001, (SELECT id from Channel WHERE name='testChannelName'), '2021-04-19 12:35:00-00', (SELECT id from EventDefinition WHERE name='subscribed'), 6790);

INSERT INTO EventAttribute (eventattributedefinitionid, eventid, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1000, 'http://localhost:8080/web/guest/home');
INSERT INTO EventAttribute (eventattributedefinitionid, eventid, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1001, 'http://localhost:8089/web/guest/home');