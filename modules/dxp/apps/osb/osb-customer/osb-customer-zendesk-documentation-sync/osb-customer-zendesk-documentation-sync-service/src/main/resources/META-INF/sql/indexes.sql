create index IX_EFCFFFF4 on OSBCustomer_ZendeskArticle (zendeskSectionId, documentationKey[$COLUMN_LENGTH:150$]);

create index IX_CFA4AE84 on OSBCustomer_ZendeskArticleAttachment (zendeskArticleId, filePath[$COLUMN_LENGTH:150$]);

create index IX_7B39F916 on OSBCustomer_ZendeskCategory (documentationKey[$COLUMN_LENGTH:75$]);

create index IX_A90C6ACC on OSBCustomer_ZendeskSection (zendeskCategoryId, documentationKey[$COLUMN_LENGTH:150$]);