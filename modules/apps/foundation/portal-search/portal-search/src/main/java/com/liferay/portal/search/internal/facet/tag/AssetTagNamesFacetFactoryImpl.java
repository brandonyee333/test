/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.facet.tag;

import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.util.FacetFactory;
import com.liferay.portal.search.facet.tag.AssetTagNamesFacetFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	service = {AssetTagNamesFacetFactory.class, FacetFactory.class}
)
public class AssetTagNamesFacetFactoryImpl
	implements AssetTagNamesFacetFactory, FacetFactory {

	@Override
	public String getFacetClassName() {
		return AssetTagNamesFacetFactory.class.getName();
	}

	@Override
	public Facet newInstance(SearchContext searchContext) {
		return new AssetTagNamesFacet(searchContext);
	}

}