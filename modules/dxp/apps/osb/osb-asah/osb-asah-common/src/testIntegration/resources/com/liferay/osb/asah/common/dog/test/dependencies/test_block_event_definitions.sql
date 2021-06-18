INSERT INTO Channel(createDate, name) VALUES('2021-04-19 00:00:00-07', 'testChannelName');

INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES(false, 'Add notification of user activity', 'Add Notification', 'addNotification', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES(false, 'Subscribed to user', 'Subscribed', 'subscribed', 'CUSTOM');
INSERT INTO EventDefinition(blocked, description, displayName, name, type) VALUES(false, 'Unsubscribed from user', 'Unsubscribed', 'unsubscribed', 'CUSTOM');

INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId) VALUES((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='addNotification')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId) VALUES((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='subscribed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition(eventAttributeDefinitionId, eventDefinitionId) VALUES((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='unsubscribed')) ON CONFLICT DO NOTHING;

INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1000, (SELECT id from Channel WHERE name='testChannelName'), '2021-04-01 00:00:00-00', (SELECT id from EventDefinition WHERE name='addNotification'));
INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1001, (SELECT id from Channel WHERE name='testChannelName'), '2021-04-19 00:00:00-00', (SELECT id from EventDefinition WHERE name='addNotification'));
INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1002, (SELECT id from Channel WHERE name='testChannelName'), '2021-03-29 00:00:00-00', (SELECT id from EventDefinition WHERE name='subscribed'));
INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1003, (SELECT id from Channel WHERE name='testChannelName'), '2021-01-20 00:00:00-00', (SELECT id from EventDefinition WHERE name='subscribed'));
INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1004, (SELECT id from Channel WHERE name='testChannelName'), '2021-01-21 00:00:00-00', (SELECT id from EventDefinition WHERE name='unsubscribed'));
INSERT INTO Event(id, channelId, eventDate, eventDefinitionId) VALUES(1005, (SELECT id from Channel WHERE name='testChannelName'), '2021-02-16 00:00:00-00', (SELECT id from EventDefinition WHERE name='unsubscribed'));

INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:8080/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1000);
INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:8089/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1001);
INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:80/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1002);
INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:8090/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1003);
INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:8086/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1004);
INSERT INTO EventAttribute(attributevalue, eventattributedefinitionid, eventid) VALUES ('http://localhost:8087/web/guest/home', (SELECT id from EventAttributeDefinition WHERE name = 'canonicalUrl'), 1005);