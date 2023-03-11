INSERT INTO Channel (createDate, id, name) VALUES ('2021-04-19 00:00:00-07', 23456, 'testChannelName');

INSERT INTO EventDefinition (applicationId, blocked, description, displayName, name, type) VALUES ('CustomEvent', false, 'Add notification of user activity', 'Add Notification', 'addNotification', 'CUSTOM');
INSERT INTO EventDefinition (applicationId, blocked, description, displayName, name, type) VALUES ('CustomEvent', false, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');
INSERT INTO EventDefinition (applicationId, blocked, description, displayName, name, type) VALUES ('CustomEvent', false, 'Unsubscribed from user', 'Unsubscribed', 'unsubscribed', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='addNotification')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='subscribed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='unsubscribed')) ON CONFLICT DO NOTHING;