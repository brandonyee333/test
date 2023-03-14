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

package com.liferay.osb.customer.downloads.display.web.internal.upgrade.v1_0_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.downloads.display.web.internal.util.DownloadsAssetCategoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(service = UpgradePortalReleaseNoteLinks.class)
public class UpgradePortalReleaseNoteLinks extends UpgradeProcess {

	public static final String PORTAL_RELEASE_NOTE_URL =
		"/group/customer/release-notes/-/release_notes/fix_pack/";

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select distinct articleId from JournalArticle where groupId " +
					"= ? and content like ?");

			ps.setLong(1, OSBCustomerConstants.GROUP_CUSTOMER_ID);
			ps.setString(
				2,
				StringPool.PERCENT + PORTAL_RELEASE_NOTE_URL +
					StringPool.PERCENT);

			rs = ps.executeQuery();

			AssetCategory productAssetCategory =
				_downloadsAssetCategoryUtil.getAssetCategory(
					AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
					"portal_62");

			while (rs.next()) {
				String articleId = rs.getString("articleId");

				JournalArticle journalArticle =
					_journalArticleLocalService.getLatestArticle(
						OSBCustomerConstants.GROUP_CUSTOMER_ID, articleId);

				List<AssetCategory> assetCategories =
					_assetCategoryLocalService.getCategories(
						JournalArticle.class.getName(),
						journalArticle.getResourcePrimKey());

				if (assetCategories.contains(productAssetCategory)) {
					updateReleaseNotesURL(journalArticle, true);
				}
				else {
					updateReleaseNotesURL(journalArticle, false);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateReleaseNotesURL(
			JournalArticle journalArticle, boolean useJIRACloudURL)
		throws Exception {

		Fields ddmFields = _journalConverter.getDDMFields(
			journalArticle.getDDMStructure(), journalArticle.getContent());

		for (Field field : ddmFields) {
			String name = field.getName();

			if (!name.equals("linkUrl")) {
				continue;
			}

			Map<Locale, List<Serializable>> valuesMap = field.getValuesMap();

			for (Map.Entry<Locale, List<Serializable>> entry :
					valuesMap.entrySet()) {

				List<Serializable> values = entry.getValue();

				for (int i = 0; i < values.size(); i++) {
					String value = GetterUtil.getString(values.get(i));

					if (Validator.isNull(value) ||
						!value.contains(PORTAL_RELEASE_NOTE_URL)) {

						continue;
					}

					String releaseNoteURL =
						"https://help.liferay.com/hc/articles/11195039376269";

					if (useJIRACloudURL) {
						int index = value.indexOf(PORTAL_RELEASE_NOTE_URL);

						int pos = index + PORTAL_RELEASE_NOTE_URL.length();

						String fixPackLabel = value.substring(pos);

						releaseNoteURL =
							"https://liferay.atlassian.net/issues" +
								"/?jql=labels=" + fixPackLabel;
					}

					values.set(i, releaseNoteURL);

					journalArticle.setContent(
						_journalConverter.getContent(
							journalArticle.getDDMStructure(), ddmFields));

					_journalArticleLocalService.updateJournalArticle(
						journalArticle);
				}
			}
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private DownloadsAssetCategoryUtil _downloadsAssetCategoryUtil;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

}