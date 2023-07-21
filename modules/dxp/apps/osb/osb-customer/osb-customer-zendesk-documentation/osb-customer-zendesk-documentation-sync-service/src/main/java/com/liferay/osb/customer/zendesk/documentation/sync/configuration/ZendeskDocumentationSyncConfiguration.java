/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Amos Fong
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfiguration",
	localization = "content/Language", name = "zendesk-documentation-sync-name"
)
public interface ZendeskDocumentationSyncConfiguration {

	@Meta.AD(
		deflt = ".markdown|.md", name = "markdown-importer-article-extensions",
		required = false
	)
	public String[] markdownImporterArticleExtensions();

	@Meta.AD(
		deflt = "intro.markdown", name = "markdown-importer-article-intro",
		required = false
	)
	public String markdownImporterArticleIntro();

	@Meta.AD(
		deflt = ".bmp|.gif|.jpeg|.jpg|.png",
		name = "markdown-importer-image-file-extensions", required = false
	)
	public String[] markdownImporterImageFileExtensions();

	@Meta.AD(
		deflt = "/images", name = "markdown-importer-image-folder",
		required = false
	)
	public String markdownImporterImageFolder();

}