INSERT INTO ExpandoColumn (columnId, dataSourceId, dataType, id, name) VALUES ('1', 123, 'STRING', '1', 'column');

INSERT INTO `Group` (dataSourceId, groupId, id, modifiedDate, name) VALUES (123, 1, '1', timestamp '${now}', 'Test');
INSERT INTO `Group` (dataSourceId, groupId, id, modifiedDate, name) VALUES (123, 2, '2', timestamp '${now}', 'Liferay');