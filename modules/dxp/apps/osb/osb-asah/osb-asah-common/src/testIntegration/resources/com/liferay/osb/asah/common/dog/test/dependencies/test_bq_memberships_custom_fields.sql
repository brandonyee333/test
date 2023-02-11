INSERT INTO BQExpandoValue (classPK, classType, columnId, dataSourceId, id, value) VALUES ('47612', 'com.liferay.portal.kernel.model.User', 'Organization', 123, '1', 'Engineer');

INSERT INTO BQIdentity (id, individualId) VALUES ('abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO BQIdentity (id, individualId) VALUES ('bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO BQIdentity (id, individualId) VALUES ('efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');

INSERT INTO BQIndividual (emailAddress, fields, id) VALUES ('joe@alpha.com', '{"classPK": "41847", "osbAsahDataSourceId" : "123"}'::json, '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO BQIndividual (emailAddress, fields, id) VALUES ('marcus@beta.com', '{"classPK": "47612", "osbAsahDataSourceId" : "123"}'::json, '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO BQIndividual (emailAddress, fields, id) VALUES ('nina@delta.com', '{"classPK": "15646", "osbAsahDataSourceId" : "123"}'::json, '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');