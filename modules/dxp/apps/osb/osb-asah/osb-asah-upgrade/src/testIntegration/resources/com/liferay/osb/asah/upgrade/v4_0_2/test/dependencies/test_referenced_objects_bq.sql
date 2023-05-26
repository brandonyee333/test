INSERT INTO Event (applicationId, assetId, assetTitle, canonicalUrl, channelId, dataSourceId, eventDate, eventId, title) VALUES ('Page', '38283', 'Title', 'http://liferay.com', 1, 405057430327289648, timestamp '${now}', 'pageViewed', 'Liferay');
INSERT INTO Event (applicationId, assetId, assetTitle, canonicalUrl, channelId, dataSourceId, eventDate, eventId, title) VALUES ('WebContent', '38284', 'Title', 'http://liferay.com', 1, 405057430327289648, timestamp '${now}', 'webContentViewed', 'Liferay');

INSERT INTO `Group` (dataSourceId, groupId, id, modifiedDate, name) VALUES (405057430327289648, 20121, 'd35323a799d2b20e8f4b8810fadf35ede578833d39c82ed86febdf8a8effa091', timestamp '${now}', 'Group 1');
INSERT INTO `Group` (dataSourceId, groupId, id, modifiedDate, name) VALUES (405057430327289648, 20125, '80f9aef63d47a1026558ae8e86fb147222fbe45506371e2fbf1d4af2f3d365a7', timestamp '${now}', 'Group 2');

INSERT INTO Organization (createDate, dataSourceId, id, modifiedDate, name, organizationId) VALUES (timestamp '${now}', 405057430327289648, 'cac2e85c942ddc3438435660bd7560ca88d1958c9c5cab73d94a79009ec31704', timestamp '${now}', 'Organization 1', 33120);

INSERT INTO Role (dataSourceId, id, modifiedDate, name, roleId) VALUES (405057430327289648, 'ae56cb3be67576e3f5bdf1687bf0455f8bfc887308f5ed685bebcc8556bf457a', timestamp '${now}', 'Role 1', 20104);
INSERT INTO Role (dataSourceId, id, modifiedDate, name, roleId) VALUES (405057430327289648, '049e28865528e58d3fec762710475567a334d1d42bbb5b85bdaf06057c6fb725', timestamp '${now}', 'Role 2', 20108);

INSERT INTO Team (dataSourceId, groupId, id, modifiedDate, name, teamId) VALUES (405057430327289648, 20121, 'a0553c756c93e08e028519bf8c6eac0f4cdf05cbe901dcd20d1f408aa4d9d6c7', timestamp '${now}', 'Team 1', 35472);
INSERT INTO Team (dataSourceId, groupId, id, modifiedDate, name, teamId) VALUES (405057430327289648, 20121, '7f2442696321361d96a5010c4275c8b91e7efb8d9f1f123352922e25410e333a', timestamp '${now}', 'Team 2', 35473);

INSERT INTO User (dataSourceId, dxpUserId, emailAddress, id, modifiedDate) VALUES (405057430327289648, 35852, 'test@liferay.com', 'a862a5211215efe759d4971c08c0b6447f30ccd3c232aaf520627adcda752e37', timestamp '${now}');

INSERT INTO UserGroup (dataSourceId, id, modifiedDate, name, userGroupId) VALUES (405057430327289648, 'a0a37f51bd1c8b6c3f06d1d86867a7d738a7bcf28b86178a6c848705575d5895', timestamp '${now}', 'User Group 1', 35452);
INSERT INTO UserGroup (dataSourceId, id, modifiedDate, name, userGroupId) VALUES (405057430327289648, '1ceafacc8249f39e93107f65fc253b5b29c2ab921695dbc3b5afaf58b898184f', timestamp '${now}', 'User Group 2', 35453);