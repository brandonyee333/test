/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Sergio González
 */
@ExtendedObjectClassDefinition(
	category = "collaboration",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.blogs.web.configuration.BlogsPortletInstanceConfiguration",
	localization = "content/Language",
	name = "blogs-portlet-instance-configuration-name"
)
public interface BlogsPortletInstanceConfiguration {

	@Meta.AD(deflt = "abstract", name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(deflt = "true", name = "enable-comment-ratings", required = false)
	public boolean enableCommentRatings();

	@Meta.AD(deflt = "true", name = "enable-comments", required = false)
	public boolean enableComments();

	@Meta.AD(deflt = "true", name = "enable-flags", required = false)
	public boolean enableFlags();

	@Meta.AD(deflt = "true", name = "enable-ratings", required = false)
	public boolean enableRatings();

	@Meta.AD(deflt = "true", name = "enable-related-assets", required = false)
	public boolean enableRelatedAssets();

	@Meta.AD(deflt = "true", name = "enable-social-bookmarks", required = false)
	public boolean enableSocialBookmarks();

	@Meta.AD(deflt = "false", name = "enable-view-count", required = false)
	public boolean enableViewCount();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/search.container.page.default.delta}",
		name = "page-delta", required = false
	)
	public String pageDelta();

	@Meta.AD(
		deflt = "bottom", name = "social-bookmarks-display-position",
		required = false
	)
	public String socialBookmarksDisplayPosition();

	@Meta.AD(
		deflt = "menu", name = "social-bookmarks-display-style",
		required = false
	)
	public String socialBookmarksDisplayStyle();

	@Meta.AD(
		deflt = "${server-property://com.liferay.portal/social.bookmark.types}",
		name = "social-bookmarks-types", required = false
	)
	public String socialBookmarksTypes();

}