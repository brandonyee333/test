INSERT INTO Event(applicationId, canonicalUrl, channelId, createDate, context, dataSourceId, eventDate, eventId, id, languageId, sessionId, userId) VALUES ('Page', 'canonicalUrlValue', 1, timestamp '${now}', '{}', 1, timestamp '${now}', 'assetClicked', 'abc-123', 'pt-BR', 'sessionId', '1');
INSERT INTO Event(applicationId, canonicalUrl, channelId, createDate, context, dataSourceId, eventDate, eventId, id, languageId, sessionId, userId) VALUES ('Page', 'canonicalUrlValue', 1, timestamp '${now}', '{}', 1, timestamp '${now}', 'assetDownloaded', 'efg-246', 'pt-BR', 'sessionId', '1');

INSERT INTO EventProperty(id, name, value) VALUES ('abc-123', 'viewDuration', 'viewDurationValue');
INSERT INTO EventProperty(id, name, value) VALUES ('efg-246', 'viewDuration', 'viewDurationValue');

INSERT INTO Identity_Raw (id, individualId) VALUES ('1', '1');

INSERT INTO Session(channelId, id, sessionEnd, sessionStart) VALUES (1, 'sessionId', timestamp '${now}', timestamp '${now}');