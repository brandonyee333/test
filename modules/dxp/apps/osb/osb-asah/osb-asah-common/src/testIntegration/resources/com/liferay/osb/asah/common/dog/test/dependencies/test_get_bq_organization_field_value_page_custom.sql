INSERT INTO ExpandoColumn (dataSourceId, className, dataType, displayType, name) VALUES (1, 'com.liferay.portal.kernel.model.Organization', 'text', 'checkbox', 'Location');
INSERT INTO ExpandoColumn (dataSourceId, className, dataType, displayType, name) VALUES (1, 'com.liferay.portal.kernel.model.Organization', 'text', 'input-field', 'Organization_Type');

INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2345', 'com.liferay.portal.kernel.model.Organization', '191', 123, 'Location', '19884', '["England", "Italy"]');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2346', 'com.liferay.portal.kernel.model.Organization', '191', 123, 'Location', '19885', '["France"]');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2347', 'com.liferay.portal.kernel.model.Organization', '191', 123, 'Location', '19886', '["England"]');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2348', 'com.liferay.portal.kernel.model.Organization', '191', 123, 'Location', '19887', '["Belgium"]');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2349', 'com.liferay.portal.kernel.model.Organization', '191', 123, 'Location', '19888', '["Germany"]');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2345', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19879', 'Type One');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2346', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19880', 'Type Two');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2347', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19881', 'Type Three');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2348', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19882', 'Type Four');
INSERT INTO ExpandoValue (classPK, classType, columnId, dataSourceId, fieldName, id, value) VALUES ('2349', 'com.liferay.portal.kernel.model.Organization', '190', 123, 'Organization_Type', '19883', 'Type Five');

INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, '23rfacq3r2r', 'Organization 1', 2345);
INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, '23qrq23rawr', 'Organization 2', 2346);
INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, 'lasoijlsdaf', 'Organization 3', 2347);
INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, '98u3o4823u4', 'Organization 4', 2348);
INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, 'zxdfawer923', 'Organization 5', 2349);
INSERT INTO Organization (dataSourceId, id, name, organizationId) VALUES (123, '2q34fadfs23', 'Test', 2350);