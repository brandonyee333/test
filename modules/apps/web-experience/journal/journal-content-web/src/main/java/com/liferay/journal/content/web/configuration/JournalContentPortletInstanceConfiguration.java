/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.journal.content.web.configuration.JournalContentPortletInstanceConfiguration",
	localization = "content/Language",
	name = "journal-content-portlet-instance-configuration-name"
)
public interface JournalContentPortletInstanceConfiguration {

	@Meta.AD(required = false)
	public String articleId();

	@Meta.AD(required = false)
	public String contentMetadataAssetAddonEntryKeys();

	@Meta.AD(required = false)
	public String ddmTemplateKey();

	@Meta.AD(required = false)
	public String enableViewCountIncrement();

	@Meta.AD(deflt = "0", required = false)
	public long groupId();

	@Meta.AD(required = false)
	public String userToolAssetAddonEntryKeys();

	@Meta.AD(
		deflt = "false", description = "sort-structures-by-name-help",
		name = "sort-structures-by-name", required = false
	)
	public boolean sortStructuresByByName();

}