/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.configuration;

import aQute.bnd.annotation.ProviderType;
import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Michael C. Han
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.search.configuration.SearchEngineHelperConfiguration",
	localization = "content/Language",
	name = "search-engine-helper-configuration-name"
)
@ProviderType
public interface SearchEngineHelperConfiguration {

	@Meta.AD(
		deflt = "com.liferay.asset.kernel.model.AssetCategory|com.liferay.asset.kernel.model.AssetEntry|com.liferay.asset.kernel.model.AssetVocabulary|com.liferay.calendar.model.Calendar|com.liferay.configuration.admin.web.model.ConfigurationModel|com.liferay.document.library.kernel.model.DLFileEntryMetadata|com.liferay.exportimport.kernel.model.ExportImportConfiguration|com.liferay.message.boards.kernel.model.MBThread|com.liferay.portal.kernel.model.Contact|com.liferay.portal.kernel.model.Organization|com.liferay.portal.kernel.model.UserGroup|com.liferay.portal.kernel.plugin.PluginPackage|com.liferay.trash.kernel.model.TrashEntry|com.liferay.wiki.model.WikiNode",
		name = "excluded-entry-class-names", required = false
	)
	public String[] excludedEntryClassNames();

}