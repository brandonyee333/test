/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.expando;

import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.util.ExpandoBridgeFactory;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.ExpandoQueryContributor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = ExpandoQueryContributor.class)
public class BaseIndexerExpandoQueryContributor
	implements ExpandoQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery, String[] classNames,
		SearchContext searchContext) {

		ExpandoQueryContributorHelper expandoQueryContributorHelper =
			new ExpandoQueryContributorHelper(
				expandoBridgeFactory, expandoBridgeIndexer,
				expandoColumnLocalService, getLocalization());

		expandoQueryContributorHelper.setAndSearch(searchContext.isAndSearch());
		expandoQueryContributorHelper.setBooleanQuery(booleanQuery);
		expandoQueryContributorHelper.setClassNamesStream(
			Stream.of(classNames));
		expandoQueryContributorHelper.setCompanyId(
			searchContext.getCompanyId());
		expandoQueryContributorHelper.setKeywords(keywords);
		expandoQueryContributorHelper.setLocale(searchContext.getLocale());

		expandoQueryContributorHelper.contribute();
	}

	protected Localization getLocalization() {

		// See LPS-72507

		if (localization != null) {
			return localization;
		}

		return LocalizationUtil.getLocalization();
	}

	@Reference
	protected ExpandoBridgeFactory expandoBridgeFactory;

	@Reference
	protected ExpandoBridgeIndexer expandoBridgeIndexer;

	@Reference
	protected ExpandoColumnLocalService expandoColumnLocalService;

	protected Localization localization;

}