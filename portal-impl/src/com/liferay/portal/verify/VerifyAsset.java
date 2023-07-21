/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portlet.asset.model.impl.AssetEntryImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Douglas Wong
 */
public class VerifyAsset extends VerifyProcess {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	protected void deleteOrphanedAssetEntries() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				DLFileEntryConstants.getClassName());

			StringBundler sb = new StringBundler(5);

			sb.append("delete from AssetEntry where classNameId = ");
			sb.append(classNameId);
			sb.append(" and classPK not in (select fileVersionId from ");
			sb.append("DLFileVersion) and classPK not in (select fileEntryId ");
			sb.append("from DLFileEntry)");

			runSQL(sb.toString());

			EntityCacheUtil.clearCache(AssetEntryImpl.class);
			FinderCacheUtil.clearCache(AssetEntryImpl.class.getName());
		}
	}

	protected void deleteOrphanLayoutAssetEntries() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long classNameId = PortalUtil.getClassNameId(
				Layout.class.getName());

			StringBundler sb = new StringBundler(3);

			sb.append("delete from AssetEntry where classNameId = ");
			sb.append(classNameId);
			sb.append(" and classPK not in (select plid from Layout)");

			runSQL(sb.toString());

			EntityCacheUtil.clearCache(AssetEntryImpl.class);
			FinderCacheUtil.clearCache(AssetEntryImpl.class.getName());
		}
	}

	@Override
	protected void doVerify() throws Exception {
		deleteOrphanLayoutAssetEntries();

		rebuildTree();
	}

	protected void rebuildTree() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select distinct groupId from AssetCategory where " +
					"(leftCategoryId is null) or (rightCategoryId is null)");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long groupId = rs.getLong("groupId");

				AssetCategoryLocalServiceUtil.rebuildTree(groupId, true);
			}
		}
	}

}