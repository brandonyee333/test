/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Roberto Díaz
 */
@ExtendedObjectClassDefinition(
	category = "collaboration",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.knowledge.base.web.configuration.KBArticlePortletInstanceConfiguration",
	localization = "content/Language",
	name = "knowledge-base-article-portlet-instance-configuration-name"
)
public interface KBArticlePortletInstanceConfiguration {

	@Meta.AD(deflt = "0", name = "resource-prim-key", required = false)
	public long resourcePrimKey();

	@Meta.AD(deflt = "0", name = "resource-class-name-id", required = false)
	public long resourceClassNameId();

	@Meta.AD(
		deflt = "false", name = "enable-kb-article-description",
		required = false
	)
	public boolean enableKBArticleDescription();

	@Meta.AD(
		deflt = "true", name = "enable-kb-article-ratings", required = false
	)
	public boolean enableKBArticleRatings();

	@Meta.AD(
		deflt = "true", name = "show-kb-article-asset-entries", required = false
	)
	public boolean showKBArticleAssetEntries();

	@Meta.AD(
		deflt = "true", name = "show-kb-article-attachments", required = false
	)
	public boolean showKBArticleAttachments();

	@Meta.AD(
		deflt = "true", name = "enable-kb-article-asset-links", required = false
	)
	public boolean enableKBArticleAssetLinks();

	@Meta.AD(
		deflt = "false", name = "enable-kb-article-view-count-increment",
		required = false
	)
	public boolean enableKBArticleViewCountIncrement();

	@Meta.AD(
		deflt = "true", name = "enable-kb-article-subscriptions",
		required = false
	)
	public boolean enableKBArticleSubscriptions();

	@Meta.AD(
		deflt = "true", name = "enable-kb-article-history", required = false
	)
	public boolean enableKBArticleHistory();

	@Meta.AD(deflt = "true", name = "enable-kb-article-print", required = false)
	public boolean enableKBArticlePrint();

	@Meta.AD(
		deflt = "false", name = "enable-social-bookmarks", required = false
	)
	public boolean enableSocialBookmarks();

	@Meta.AD(
		deflt = "menu", name = "social-bookmarks-display-style",
		required = false
	)
	public String socialBookmarksDisplayStyle();

	@Meta.AD(
		deflt = "bottom", name = "social-bookmarks-display-position",
		required = false
	)
	public String socialBookmarksDisplayPosition();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/social.bookmark.types}",
		name = "social-bookmarks-types", required = false
	)
	public String socialBookmarksTypes();

}