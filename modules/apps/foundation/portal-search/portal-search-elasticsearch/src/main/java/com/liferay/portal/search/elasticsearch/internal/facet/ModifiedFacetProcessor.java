/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.facet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Tibor Lipusz
 */
@Component(
	immediate = true,
	property = "class.name=com.liferay.portal.kernel.search.facet.ModifiedFacet",
	service = FacetProcessor.class
)
public class ModifiedFacetProcessor extends RangeFacetProcessor {
}