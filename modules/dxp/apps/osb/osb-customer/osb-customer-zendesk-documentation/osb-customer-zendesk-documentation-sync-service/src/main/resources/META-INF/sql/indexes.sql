create index IX_E5D58AFB on OSBCustomer_ZendeskArticle (documentationOriginalURL[$COLUMN_LENGTH:255$]);
create index IX_AE645B9B on OSBCustomer_ZendeskArticle (zendeskCategoryId, documentationKey[$COLUMN_LENGTH:150$]);
create index IX_E374CBBB on OSBCustomer_ZendeskArticle (zendeskSectionId);

create index IX_CFA4AE84 on OSBCustomer_ZendeskArticleAttachment (zendeskArticleId, filePath[$COLUMN_LENGTH:150$]);

create index IX_7B39F916 on OSBCustomer_ZendeskCategory (documentationKey[$COLUMN_LENGTH:75$]);

create index IX_A90C6ACC on OSBCustomer_ZendeskSection (zendeskCategoryId, documentationKey[$COLUMN_LENGTH:150$]);