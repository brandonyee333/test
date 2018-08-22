/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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