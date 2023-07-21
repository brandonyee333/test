/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.web.internal.search.indexer;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackField;
import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseAssetCategoryProperty;
import com.liferay.osb.customer.release.tool.web.internal.util.ReleasesAssetCategoryUtil;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.journal.model.JournalArticle",
	service = IndexerPostProcessor.class
)
public class JournalArticleIndexerPostProcessor
	extends BaseIndexerPostProcessor {

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {

		JournalArticle journalArticle = (JournalArticle)obj;

		AssetCategory assetCategory =
			_releasesAssetCategoryUtil.fetchFixPackAssetCategory(
				journalArticle.getResourcePrimKey());

		if (assetCategory == null) {
			return;
		}

		String version = _releasesAssetCategoryUtil.getPropertyValue(
			assetCategory.getCategoryId(),
			ReleaseAssetCategoryProperty.VERSION);

		if (Validator.isNull(version)) {
			return;
		}

		document.addNumber(
			FixPackField.FIX_PACK_VERSION, GetterUtil.getDouble(version));

		String product = _releasesAssetCategoryUtil.getPropertyValue(
			assetCategory.getParentCategoryId(),
			ReleaseAssetCategoryProperty.PRODUCT);

		document.addKeyword(FixPackField.PRODUCT, product);

		String productVersion = _releasesAssetCategoryUtil.getPropertyValue(
			assetCategory.getParentCategoryId(),
			ReleaseAssetCategoryProperty.VERSION);

		document.addNumber(
			FixPackField.PRODUCT_VERSION, GetterUtil.getDouble(productVersion));
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private ReleasesAssetCategoryUtil _releasesAssetCategoryUtil;

}