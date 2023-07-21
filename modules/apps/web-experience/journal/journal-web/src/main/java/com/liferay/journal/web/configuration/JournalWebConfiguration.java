/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jürgen Kappler
 */
@ExtendedObjectClassDefinition(category = "web-experience")
@Meta.OCD(
	id = "com.liferay.journal.web.configuration.JournalWebConfiguration",
	localization = "content/Language", name = "journal-web-configuration-name"
)
public interface JournalWebConfiguration {

	@Meta.AD(
		deflt = "false",
		description = "changeable-default-language-description",
		name = "changeable-default-language", required = false
	)
	public boolean changeableDefaultLanguage();

	@Meta.AD(deflt = "15", required = false)
	public int checkInterval();

	@Meta.AD(deflt = "descriptive", required = false)
	public String defaultDisplayView();

	@Meta.AD(deflt = "icon|descriptive|list", required = false)
	public String[] displayViews();

	@Meta.AD(deflt = "true", required = false)
	public boolean journalArticleForceAutogenerateId();

	@Meta.AD(deflt = "true", required = false)
	public boolean journalArticlesSearchWithIndex();

	@Meta.AD(
		deflt = "false",
		description = "journal-browse-by-structures-sorted-by-name-help",
		name = "journal-browse-by-structures-sorted-by-name", required = false
	)
	public boolean journalBrowseByStructuresSortedByName();

	@Meta.AD(deflt = "true", required = false)
	public boolean journalFeedForceAutogenerateId();

	@Meta.AD(deflt = "true", required = false)
	public boolean publishToLiveByDefault();

	@Meta.AD(deflt = "true", required = false)
	public boolean publishVersionHistoryByDefault();

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(
		deflt = "false",
		description = "reverse-chronological-order-by-default-description",
		name = "reverse-chronological-order-by-default", required = false
	)
	public boolean reverseChronologicalOrderByDefault();

}