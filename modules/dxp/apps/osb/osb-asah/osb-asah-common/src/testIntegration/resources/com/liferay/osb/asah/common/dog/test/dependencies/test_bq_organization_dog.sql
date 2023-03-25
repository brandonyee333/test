INSERT INTO ExpandoColumn (columnId, dataSourceId, dataType, id, name) VALUES ('1', 123, 'STRING', '1', 'column');

INSERT INTO Organization (dataSourceId, id, modifiedDate, name, organizationId) VALUES (123, '1', timestamp '${now}', 'Test', 1);
INSERT INTO Organization (dataSourceId, id, modifiedDate, name, organizationId) VALUES (123, '2', timestamp '${now}', 'Liferay', 2);