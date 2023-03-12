INSERT INTO ExpandoColumn (columnId, dataSourceId, dataType, id, name) VALUES ('1', 123, 'STRING', '1', 'column');

INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, id, value) VALUES ('2', 'com.liferay.portal.kernel.model.User', '1', 123, '1', 'test');

INSERT INTO User (dataSourceId, dxpUserId, firstName, id, modifiedDate) VALUES (123, 1, 'Test', '1', timestamp '${now}');
INSERT INTO User (dataSourceId, dxpUserId, firstName, id, modifiedDate) VALUES (123, 2, 'Liferay', '2', timestamp '${now}');