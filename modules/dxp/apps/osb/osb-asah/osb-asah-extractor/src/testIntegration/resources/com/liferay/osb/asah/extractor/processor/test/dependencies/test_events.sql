INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'canonicalUrl', 'canonicalUrl', 'GLOBAL') ON CONFLICT DO NOTHING;
INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'pageDescription', 'pageDescription', 'GLOBAL') ON CONFLICT DO NOTHING;
INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'pageKeywords', 'pageKeywords', 'GLOBAL') ON CONFLICT DO NOTHING;
INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'pageTitle', 'pageTitle', 'GLOBAL') ON CONFLICT DO NOTHING;
INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'referrer', 'referrer', 'GLOBAL') ON CONFLICT DO NOTHING;
INSERT INTO EventAttributeDefinition (dataType, displayName, name, type) VALUES ('STRING', 'url', 'url', 'GLOBAL') ON CONFLICT DO NOTHING;

INSERT INTO EventDefinition (displayName, hidden, name, type) VALUES ('pageViewed', false, 'pageViewed', 'DEFAULT') ON CONFLICT DO NOTHING;

INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='canonicalUrl'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageDescription'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageKeywords'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageTitle'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='referrer'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;
INSERT INTO EventDefinitionEventAttributeDefinition (eventAttributeDefinitionId, eventDefinitionId) VALUES ((SELECT id from EventAttributeDefinition WHERE name='url'), (SELECT id from EventDefinition WHERE name='pageViewed')) ON CONFLICT DO NOTHING;

INSERT INTO Event (analyticsEventId, id, channelId, eventDate, eventDefinitionId, sessionId) VALUES ('2271fee7c2d3f547c3a9aca6e93aa0253be8cbc1823e47484ba1a7471fa5ae99', 1000, 123, '2020-03-16T09:34:45.340', (SELECT id from EventDefinition WHERE name='pageViewed'), 3000);

INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageDescription'), '2020-03-16T09:34:45.340', 1000, 2000, 'This is the Liferay Analytics JS client test page.');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageKeywords'), '2020-03-16T09:34:45.340', 1000, 2001, 'Liferay, Analytics, Test, Javascript');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='pageTitle'), '2020-03-16T09:34:45.340', 1000, 2002, 'Liferay Analytics JS Client Test Page');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='referrer'), '2020-03-16T09:34:45.340', 1000, 2003, 'https://www.google.com');
INSERT INTO EventAttribute (eventAttributeDefinitionId, eventDate, eventId, id, value) VALUES ((SELECT id from EventAttributeDefinition WHERE name='url'), '2020-03-16T09:34:45.340', 1000, 2004, 'https://www.liferay.com');
