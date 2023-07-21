/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.tag;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.MultiValueFacet;

/**
 * @author André de Oliveira
 */
public class AssetTagNamesFacet extends MultiValueFacet implements Facet {

	public AssetTagNamesFacet(SearchContext searchContext) {
		super(searchContext);

		setFieldName(Field.ASSET_TAG_NAMES + ".raw");
	}

}