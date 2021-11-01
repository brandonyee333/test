CREATE INDEX IF NOT EXISTS IX_EVENT_CIEDEDI ON Event (channelId, eventDate, eventDefinitionId);

CREATE INDEX IF NOT EXISTS IX_EVENT_CIEDII ON Event (channelId, eventDate, individualId);

CREATE INDEX IF NOT EXISTS IX_EVENT_EVENTDATE ON Event (eventDate);

CREATE INDEX IF NOT EXISTS IX_EVENTATTRIBUTE_EADIED ON EventAttribute (eventattributeDefinitionId, eventDate);

CREATE INDEX IF NOT EXISTS IX_EVENTATTRIBUTE_EADIEI ON EventAttribute (eventAttributeDefinitionId, eventId);

CREATE INDEX IF NOT EXISTS IX_EVENTATTRIBUTE_EVENTID ON EventAttribute (eventId);

CREATE INDEX IF NOT EXISTS IX_EVENTATTRIBUTEDEFINITION_NAME ON EventAttributeDefinition (name);

CREATE INDEX IF NOT EXISTS IX_EVENTDEFINITION_NAME ON EventDefinition (name);

CREATE INDEX IF NOT EXISTS IX_INTEREST_OIOT ON Interest (ownerId, ownerType);

CREATE INDEX IF NOT EXISTS IX_MEMBERSHIPCHANGE_ISIMD ON MembershipChange (individualsegmentid, modifieddate);

CREATE UNIQUE INDEX IF NOT EXISTS IX_ACTIVITYGROUP_ATCIDSIDDUI ON ActivityGroup (activityType, channelId, dataSourceId, dayDate, userId);

CREATE UNIQUE INDEX IF NOT EXISTS IX_EVENTATTRIBUTE_EADIEDEI ON EventAttribute (eventAttributeDefinitionId, eventDate, eventId);

CREATE UNIQUE INDEX IF NOT EXISTS IX_INTEREST_NOIOTRD ON Interest (name, ownerId, ownerType, recordedDate);

CREATE UNIQUE INDEX IF NOT EXISTS IX_MEMBERSHIP_IIDISIDS ON Membership (individualId, individualSegmentId, status);