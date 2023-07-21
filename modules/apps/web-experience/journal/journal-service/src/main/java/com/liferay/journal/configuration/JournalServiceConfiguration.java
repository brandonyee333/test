/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.configuration;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Pavel Savinov
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.journal.configuration.JournalServiceConfiguration",
	localization = "content/Language",
	name = "journal-service-configuration-name"
)
@ProviderType
public interface JournalServiceConfiguration {

	@Meta.AD(deflt = "true", required = false)
	public boolean addDefaultStructures();

	@Meta.AD(
		deflt = "&|\\'|@|\\\\|]|}|:|=|>|/|<|[|{|%|+|#|`|?|\\\"|;|*|~",
		description = "specifcy-characters-that-are-not-allowed-in-journal-folder-names",
		required = false
	)
	public String[] charactersblacklist();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.ftl}",
		required = false
	)
	public String errorTemplateFTL();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.vm}",
		required = false
	)
	public String errorTemplateVM();

	@Meta.AD(
		deflt = "${resource:com/liferay/journal/dependencies/error.xsl}",
		required = false
	)
	public String errorTemplateXSL();

	@Meta.AD(deflt = "900000", required = false)
	public long checkInterval();

	@Meta.AD(
		deflt = "", description = "journal-article-custom-token-names",
		required = false
	)
	public String[] customTokenNames();

	@Meta.AD(
		deflt = "", description = "journal-article-custom-token-values",
		required = false
	)
	public String[] customTokenValues();

	@Meta.AD(
		deflt = "true", description = "journal-article-comments",
		required = false
	)
	public boolean articleCommentsEnabled();

	@Meta.AD(
		deflt = "true",
		description = "journal-article-database-search-content-keywords",
		required = false
	)
	public boolean databaseContentKeywordSearchEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-article-expire-all-versions",
		required = false
	)
	public boolean expireAllArticleVersionsEnabled();

	@Meta.AD(
		deflt = "false", description = "journal-article-view-permission-check",
		required = false
	)
	public boolean articleViewPermissionsCheckEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-article-index-all-versions",
		required = false
	)
	public boolean indexAllArticleVersionsEnabled();

	@Meta.AD(
		deflt = "true", description = "journal-folder-icon-check-count",
		required = false
	)
	public boolean folderIconCheckCountEnabled();

	@Meta.AD(
		deflt = "true", description = "publish-to-live-by-default",
		required = false
	)
	public boolean publishToLiveByDefaultEnabled();

	@Meta.AD(
		deflt = "true", description = "publish-version-history-by-default",
		required = false
	)
	public boolean versionHistoryByDefaultEnabled();

	@Meta.AD(
		deflt = "false", description = "sync-content-search-on-startup",
		required = false
	)
	public boolean syncContentSearchOnStartup();

	@Meta.AD(
		deflt = "@page_break@",
		description = "journal-article-token-page-break", required = false
	)
	public String journalArticlePageBreakToken();

	@Meta.AD(
		deflt = "json", description = "journal-article-storage-type",
		required = false
	)
	public String journalArticleStorageType();

}