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
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseAssetCategoryProperty;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ReleasesAssetCategoryUtil.class)
public class ReleasesAssetCategoryUtil {

	public static final String FIND_BY_V_PC =
		ReleasesAssetCategoryUtil.class.getName() + ".findByV_PC";

	public static final String JOIN_BY_ASSET_CATEGORY_PROPERTY =
		ReleasesAssetCategoryUtil.class.getName() +
			".joinByAssetCategoryProperty";

	public AssetCategory fetchFixPackAssetCategory(
			long journalArticleResourcePrimKey)
		throws PortalException {

		List<AssetCategory> assetCategories =
			_assetCategoryLocalService.getCategories(
				JournalArticle.class.getName(), journalArticleResourcePrimKey);

		for (AssetCategory assetCategory : assetCategories) {
			if (assetCategory.getVocabularyId() ==
					_releasesAssetVocabulary.getVocabularyId()) {

				return assetCategory;
			}
		}

		return null;
	}

	public AssetCategory getFixPackAssetCategory(
		long parentCategoryId, double version) {

		Map<String, String> assetCategoryProperties = new HashMap<>();

		assetCategoryProperties.put(
			ReleaseAssetCategoryProperty.VERSION, String.valueOf(version));

		return getAssetCategory(parentCategoryId, assetCategoryProperties);
	}

	public AssetCategory getProductAssetCategory(
		String product, double productVersion) {

		Map<String, String> assetCategoryProperties = new HashMap<>();

		assetCategoryProperties.put(ReleaseAssetCategoryProperty.PRODUCT, product);
		assetCategoryProperties.put(
			ReleaseAssetCategoryProperty.VERSION, String.valueOf(productVersion));

		return getAssetCategory(
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
			assetCategoryProperties);
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

	public long getReleasesAssetVocabularyId() {
		return _releasesAssetVocabulary.getVocabularyId();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws PortalException {

		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		Group group = _groupLocalService.getCompanyGroup(companyId);

		_releasesAssetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				group.getGroupId(), _VOCABULARY_RELEASES_NAME);
	}

	protected AssetCategory getAssetCategory(
		long parentCategoryId, Map<String, String> assetCategoryProperties) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_V_PC);

			sql = StringUtil.replace(
				sql, "[$JOIN$]", getJoin(assetCategoryProperties));
			sql = StringUtil.replace(
				sql, "[$WHERE$]", getWhere(assetCategoryProperties));

			ps = con.prepareStatement(sql);

			int pos = setJoin(ps, assetCategoryProperties);

			ps.setLong(pos, _releasesAssetVocabulary.getVocabularyId());
			ps.setLong(pos + 1, parentCategoryId);

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

	protected String getJoin(Map<String, String> assetCategoryProperties) {
		StringBundler sb = new StringBundler(assetCategoryProperties.size());

		for (int i = 0; i < assetCategoryProperties.size(); i++) {
			String join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ASSET_CATEGORY_PROPERTY);

			join = StringUtil.replace(join, "[$i$]", String.valueOf(i));

			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}

			sb.append(join);
		}

		return sb.toString();
	}

	protected String getWhere(Map<String, String> assetCategoryProperties) {
		StringBundler sb = new StringBundler(assetCategoryProperties.size());

		for (int i = 0; i < assetCategoryProperties.size(); i++) {
			String join = CustomSQLUtil.get(
				getClass(), JOIN_BY_ASSET_CATEGORY_PROPERTY);

			join = StringUtil.replace(join, "[$i$]", String.valueOf(i));

			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5) + " AND ";
			}

			sb.append(join);
		}

		return sb.toString();
	}

	protected int setJoin(
			PreparedStatement ps, Map<String, String> assetCategoryProperties)
		throws Exception {

		int pos = 1;

		for (Map.Entry<String, String> entry :
				assetCategoryProperties.entrySet()) {

			ps.setString(pos, entry.getKey());
			ps.setString(pos + 1, entry.getValue());

			pos = pos + 2;
		}

		return pos;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final String _VOCABULARY_RELEASES_NAME = "Releases";

	private static final Log _log = LogFactoryUtil.getLog(
		ReleasesAssetCategoryUtil.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetCategoryPropertyLocalService
		_assetCategoryPropertyLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	private AssetVocabulary _releasesAssetVocabulary;

}