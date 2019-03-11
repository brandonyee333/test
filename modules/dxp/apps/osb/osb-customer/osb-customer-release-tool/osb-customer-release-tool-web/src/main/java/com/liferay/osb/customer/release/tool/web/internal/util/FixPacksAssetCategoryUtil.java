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

package com.liferay.osb.customer.release.tool.web.internal.util;

import com.liferay.asset.kernel.exception.NoSuchCategoryPropertyException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackAssetCategoryConstants;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = FixPacksAssetCategoryUtil.class)
public class FixPacksAssetCategoryUtil {

	public static final String FIND_BY_JPV_FPV =
		FixPacksAssetCategoryUtil.class.getName() + ".findByJPV_FPV";

	public AssetCategory fetchFixPackAssetCategory(
			long journalArticleResourcePrimKey)
		throws PortalException {

		List<AssetCategory> assetCategories =
			_assetCategoryLocalService.getCategories(
				JournalArticle.class.getName(), journalArticleResourcePrimKey);

		for (AssetCategory assetCategory : assetCategories) {
			if (assetCategory.getVocabularyId() ==
					_fixPacksAssetVocabulary.getVocabularyId()) {

				return assetCategory;
			}
		}

		return null;
	}

	public AssetCategory getFixPackAssetCategory(
		String jiraProductVersion, double fixPackVersion) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(
				FixPacksAssetCategoryUtil.class, FIND_BY_JPV_FPV);

			ps = con.prepareStatement(sql);

			ps.setLong(1, _fixPacksAssetVocabulary.getVocabularyId());
			ps.setString(2, FixPackAssetCategoryConstants.PROPERTY_VERSION);
			ps.setString(3, String.valueOf(fixPackVersion));
			ps.setString(
				4, FixPackAssetCategoryConstants.PROPERTY_JIRA_PRODUCT_VERSION);
			ps.setString(5, jiraProductVersion);

			rs = ps.executeQuery();

			if (rs.next()) {
				return _assetCategoryLocalService.getCategory(rs.getLong(1));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return null;
	}

	public long getFixPacksAssetVocabularyId() {
		return _fixPacksAssetVocabulary.getVocabularyId();
	}

	public List<AssetCategory> getProductAssetCategories(
			String product, double fromProductVersion, double toProductVersion)
		throws PortalException {

		String property =
			FixPackAssetCategoryConstants.PROPERTY_PRODUCT + StringPool.COLON +
				product;

		List<AssetCategory> productAssetCategories = new ArrayList<>();

		List<AssetCategory> assetCategories = _assetCategoryLocalService.search(
			_fixPacksAssetVocabulary.getGroupId(), null,
			new String[] {property}, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AssetCategory assetCategory : assetCategories) {
			double productVersion = GetterUtil.getDouble(
				getPropertyValue(
					assetCategory.getCategoryId(),
					FixPackAssetCategoryConstants.PROPERTY_VERSION));

			if ((productVersion >= fromProductVersion) &&
				(productVersion <= toProductVersion)) {

				productAssetCategories.add(assetCategory);
			}
		}

		return productAssetCategories;
	}

	public String getPropertyValue(long assetCategoryId, String key)
		throws PortalException {

		try {
			AssetCategoryProperty assetCategoryProperty =
				_assetCategoryPropertyLocalService.getCategoryProperty(
					assetCategoryId, key);

			return assetCategoryProperty.getValue();
		}
		catch (NoSuchCategoryPropertyException nscpe) {
		}

		return StringPool.BLANK;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws PortalException {

		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		Group group = _groupLocalService.getCompanyGroup(companyId);

		_fixPacksAssetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				group.getGroupId(),
				FixPackAssetCategoryConstants.VOCABULARY_FIX_PACKS_NAME);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FixPacksAssetCategoryUtil.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetCategoryPropertyLocalService
		_assetCategoryPropertyLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private AssetVocabulary _fixPacksAssetVocabulary;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}