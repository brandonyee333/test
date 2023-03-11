INSERT INTO Channel (createDate, id, name) VALUES ('2021-04-19 00:00:00-07', 12345, 'testChannelName');

INSERT INTO EventDefinition (applicationId, blocked, description, displayName, name, type) VALUES ('CustomEvent', false, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='subscribed')) ON CONFLICT DO NOTHING;