INSERT INTO ExpandoColumn (columnId, dataSourceId, dataType, id, name) VALUES ('1', 123, 'STRING', '1', 'column');

INSERT INTO Role (dataSourceId, id, modifiedDate, name, roleId) VALUES (123, '1', timestamp '${now}', 'Test', 1);
INSERT INTO Role (dataSourceId, id, modifiedDate, name, roleId) VALUES (123, '2', timestamp '${now}', 'Liferay', 2);