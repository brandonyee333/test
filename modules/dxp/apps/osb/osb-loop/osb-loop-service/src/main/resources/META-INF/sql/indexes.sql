create index IX_14E02004 on LoopDivision (companyId, type_);
create unique index IX_E8A24B9 on LoopDivision (organizationId);

create unique index IX_660D0EBC on LoopDivisionRel (childLoopDivisionId, loopPersonId, parentLoopDivisionId);
create index IX_83B74940 on LoopDivisionRel (loopPersonId, parentLoopDivisionId);

create unique index IX_2EC87B53 on LoopExternalReferenceRel (externalReferenceName[$COLUMN_LENGTH:75$], externalReferencePK[$COLUMN_LENGTH:75$]);

create unique index IX_8987F784 on LoopJobTitle (name[$COLUMN_LENGTH:75$]);

create unique index IX_764F712A on LoopParticipantAssignment (loopDivisionId, loopPersonId);

create unique index IX_141BF7AE on LoopPerson (personUserId);

create index IX_AB03F511 on LoopStreamEntry (loopPersonId, classNameId, classPK);
create unique index IX_84B42984 on LoopStreamEntry (loopPersonId, loopStreamId, classNameId, classPK);
create index IX_E8202A32 on LoopStreamEntry (loopPersonId, loopStreamId, following);

create unique index IX_C95F9836 on LoopTopic (companyId, name[$COLUMN_LENGTH:75$]);

create index IX_C3DEC7F4 on LoopUserNotificationEvent (groupClassNameId, groupClassPK, type_);
create index IX_AE7E421A on LoopUserNotificationEvent (groupKey);

create unique index IX_812B1E9C on LoopUserNotificationSubscription (loopPersonId, classNameId, classPK, deliveryType);