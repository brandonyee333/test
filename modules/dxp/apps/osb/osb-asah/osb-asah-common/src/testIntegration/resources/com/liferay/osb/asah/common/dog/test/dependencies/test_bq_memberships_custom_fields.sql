INSERT INTO ExpandoColumn (className, columnId, dataSourceId, dataType, displayType, id, modifiedDate, name) VALUES ('com.liferay.portal.kernel.model.User', 'Organization-Text', 123, 'Text', 'input-field', '432', timestamp '2019-05-15T00:00:00.000-00', 'Organization-Text');

INSERT INTO Identity_Raw (id, individualId) VALUES ('abc-123', '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Identity_Raw (id, individualId) VALUES ('bcd-456', '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Identity_Raw (id, individualId) VALUES ('efg-789', '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');

INSERT INTO Individual (emailAddress, fields, id) VALUES ('joe@alpha.com', ARRAY[STRUCT(123, 'classPK', '41847'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer')], '761319ac0d9f6e0f3467ad26bc8c63989d06c5f491849d6aa12fabdbd6c6b7bb');
INSERT INTO Individual (emailAddress, fields, id) VALUES ('marcus@beta.com', ARRAY[STRUCT(123, 'classPK', '47612'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Engineer')], '5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a');
INSERT INTO Individual (emailAddress, fields, id) VALUES ('nina@delta.com', ARRAY[STRUCT(123, 'classPK', '15646'), STRUCT(123, 'osbAsahDataSourceId', '123'), STRUCT(123, 'Organization', 'Developer')], '5f20f61b2cfaa86c4f3cb3557751a702776af029deabed8e943fb55cfa604e34');