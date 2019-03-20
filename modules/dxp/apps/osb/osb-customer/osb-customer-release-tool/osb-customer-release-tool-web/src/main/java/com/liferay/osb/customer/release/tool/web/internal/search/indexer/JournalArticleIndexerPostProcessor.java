/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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