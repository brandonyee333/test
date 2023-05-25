INSERT INTO Channel (createDate, id, name, state) VALUES (timestamp '2020-04-14T08:30:00.000Z', 1, 'Site 1', 'READY');

INSERT INTO ChannelDataSource (channelId, dataSourceId, groupIds) VALUES (1, 405057430327289648, '{123456}');

INSERT INTO DataSource (id) VALUES (405057430327289648);

INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057435148143169', '405057430327289648', '{"groupId": "20121", "name": "Guest"}', timestamp '2020-04-14T08:30:00.000Z', 'GROUP');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057436480425079', '405057430327289648', '{"groupId": "20125", "name": "Global"}', timestamp '2020-04-14T08:30:00.000Z', 'GROUP');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('402139267512234420', '405057430327289648', '{"name": "sales engineering", "organizationPK": 33120}', timestamp '2020-04-14T08:30:00.000Z', 'ORGANIZATION');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('402139268847589064', '405057430327289648', '{"name": "engineering", "organizationPK": 33134}', timestamp '2020-04-14T08:30:00.000Z', 'ORGANIZATION');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('402139268847589080', '405057430327289648', '{"name": "marketing", "organizationPK": 33140}', timestamp '2020-04-14T08:30:00.000Z', 'ORGANIZATION');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057439101255435', '405057430327289648', '{"name": "Administrator", "roleId": "20104"}', timestamp '2020-04-14T08:30:00.000Z', 'ROLE');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057439859160765', '405057430327289648', '{"name": "Power User", "roleId": "20108"}', timestamp '2020-04-14T08:30:00.000Z', 'ROLE');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057439817243554', '405057430327289648', '{"name": "Owner", "roleId": "20107"}', timestamp '2020-04-14T08:30:00.000Z', 'ROLE');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057439774488100', '405057430327289648', '{"name": "Guest", "roleId": "20106"}', timestamp '2020-04-14T08:30:00.000Z', 'ROLE');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057437515771690', '405057430327289648', '{"groupId": 20121, "name": "teamA", "teamId": "35472"}', timestamp '2020-04-14T08:30:00.000Z', 'TEAM');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057437515771691', '405057430327289648', '{"groupId": 20121, "name": "teamB", "teamId": "35473"}', timestamp '2020-04-14T08:30:00.000Z', 'TEAM');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057437515771692', '405057430327289648', '{"groupId": 20121, "name": "teamC", "teamId": "35474"}', timestamp '2020-04-14T08:30:00.000Z', 'TEAM');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057437515771693', '405057430327289648', '{"groupId": 20123, "name": "teamD", "teamId": "35475"}', timestamp '2020-04-14T08:30:00.000Z', 'TEAM');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057437515771694', '405057430327289648', '{"groupId": 20122, "name": "teamE", "teamId": "35476"}', timestamp '2020-04-14T08:30:00.000Z', 'TEAM');
INSERT INTO DXPEntity (id, dataSourceId, fields, modifiedDate, type) VALUES ('405057436532876741', '405057430327289648', '{"name": "Mac Users", "userGroupId": "35452"}', timestamp '2020-04-14T08:30:00.000Z', 'USER_GROUP');