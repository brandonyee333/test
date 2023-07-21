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
	id = "com.liferay.portal.search.configuration.QueryPreProcessConfiguration",
	localization = "content/Language",
	name = "query-pre-process-configuration-name"
)
@ProviderType
public interface QueryPreProcessConfiguration {

	@Meta.AD(
		deflt = "assetCategoryTitles?(_.+)?|emailAddress|license|path|screenName|tag|treePath|userName",
		name = "field-name-patterns", required = false
	)
	public String[] fieldNamePatterns();

}