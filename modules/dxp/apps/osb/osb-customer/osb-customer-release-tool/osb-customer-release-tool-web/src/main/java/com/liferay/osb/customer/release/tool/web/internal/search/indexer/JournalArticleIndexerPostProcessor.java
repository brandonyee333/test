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
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackAssetCategoryConstants;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackField;
import com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil;
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
			_fixPacksAssetCategoryUtil.fetchFixPackAssetCategory(
				journalArticle.getResourcePrimKey());

		if (assetCategory != null) {
			String version = _fixPacksAssetCategoryUtil.getPropertyValue(
				assetCategory.getCategoryId(),
				FixPackAssetCategoryConstants.PROPERTY_VERSION);

			if (Validator.isNotNull(version)) {
				document.addNumber(
					FixPackField.FIX_PACK_VERSION,
					GetterUtil.getDouble(version));

				String product = _fixPacksAssetCategoryUtil.getPropertyValue(
					assetCategory.getParentCategoryId(),
					FixPackAssetCategoryConstants.PROPERTY_PRODUCT);

				document.addKeyword(FixPackField.PRODUCT, product);

				String productVersion =
					_fixPacksAssetCategoryUtil.getPropertyValue(
						assetCategory.getParentCategoryId(),
						FixPackAssetCategoryConstants.PROPERTY_VERSION);

				document.addNumber(
					FixPackField.PRODUCT_VERSION,
					GetterUtil.getDouble(productVersion));

				break;
			}
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private FixPacksAssetCategoryUtil _fixPacksAssetCategoryUtil;

}