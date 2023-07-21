/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.searcher;

import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.util.ExpandoBridgeFactory;
import com.liferay.expando.kernel.util.ExpandoBridgeIndexer;
import com.liferay.portal.kernel.search.IndexSearcherHelper;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchEngineHelper;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcher;
import com.liferay.portal.kernel.search.facet.faceted.searcher.FacetedSearcherManager;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.internal.expando.ExpandoQueryContributorHelper;
import com.liferay.portal.search.permission.SearchPermissionFilterContributor;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = FacetedSearcherManager.class)
public class FacetedSearcherManagerImpl implements FacetedSearcherManager {

	@Override
	public FacetedSearcher createFacetedSearcher() {
		return new FacetedSearcherImpl(
			new ExpandoQueryContributorHelper(
				expandoBridgeFactory, expandoBridgeIndexer,
				expandoColumnLocalService, getLocalization()),
			groupLocalService, indexerRegistry, indexSearcherHelper,
			searchEngineHelper, _searchPermissionFilterContributors);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSearchPermissionFilterContributor(
		SearchPermissionFilterContributor searchPermissionFilterContributor) {

		_searchPermissionFilterContributors.add(
			searchPermissionFilterContributor);
	}

	protected Localization getLocalization() {

		// See LPS-72507

		if (localization != null) {
			return localization;
		}

		return LocalizationUtil.getLocalization();
	}

	protected void removeSearchPermissionFilterContributor(
		SearchPermissionFilterContributor searchPermissionFilterContributor) {

		_searchPermissionFilterContributors.remove(
			searchPermissionFilterContributor);
	}

	@Reference
	protected ExpandoBridgeFactory expandoBridgeFactory;

	@Reference
	protected ExpandoBridgeIndexer expandoBridgeIndexer;

	@Reference
	protected ExpandoColumnLocalService expandoColumnLocalService;

	@Reference
	protected GroupLocalService groupLocalService;

	@Reference
	protected IndexerRegistry indexerRegistry;

	@Reference
	protected IndexSearcherHelper indexSearcherHelper;

	protected Localization localization;

	@Reference
	protected SearchEngineHelper searchEngineHelper;

	private final Collection<SearchPermissionFilterContributor>
		_searchPermissionFilterContributors = new CopyOnWriteArrayList<>();

}