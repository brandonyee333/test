/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Michael Bowerman
 */
@ExtendedObjectClassDefinition(
	category = "web-experience",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.exportimport.configuration.ExportImportServiceConfiguration",
	localization = "content/Language",
	name = "export-import-service-configuration-name"
)
public interface ExportImportServiceConfiguration {

	@Meta.AD(
		deflt = "true", description = "validate-file-entry-references-help",
		name = "validate-file-entry-references", required = false
	)
	public boolean validateFileEntryReferences();

	@Meta.AD(
		deflt = "true", description = "validate-journal-feed-references-help",
		name = "validate-journal-feed-references", required = false
	)
	public boolean validateJournalFeedReferences();

	@Meta.AD(
		deflt = "true", description = "validate-layout-references-help",
		name = "validate-layout-references", required = false
	)
	public boolean validateLayoutReferences();

	@Meta.AD(
		deflt = "true", description = "validate-missing-references-help",
		name = "validate-missing-references", required = false
	)
	public boolean validateMissingReferences();

}