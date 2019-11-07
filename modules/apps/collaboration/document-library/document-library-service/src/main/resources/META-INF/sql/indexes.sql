create unique index IX_18641746 on DLFileEntryPreview (fileEntryId, fileVersionId);
create index IX_E04CB467 on DLFileEntryPreview (fileVersionId);

create unique index IX_9B6A9A0 on DLFileVersionPreview (fileEntryId, fileVersionId);
create index IX_E43957CD on DLFileVersionPreview (fileVersionId);

create unique index IX_FBCCD118 on FileVersionPreview (fileEntryId, fileVersionId);
create index IX_28E06D55 on FileVersionPreview (fileVersionId);