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

package com.liferay.osb.customer.web.internal.upgrade.v1_2_0;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Jenny Chen
 */
public class UpgradeJournalArticleURLTitle extends UpgradeProcess {

	public UpgradeJournalArticleURLTitle(
		JournalArticleLocalService journalArticleLocalService,
		JournalArticlePersistence journalArticlePersistence) {

		_journalArticleLocalService = journalArticleLocalService;
		_journalArticlePersistence = journalArticlePersistence;
	}

	protected static String getUrlTitle(long id, String title) {
		if (title == null) {
			return String.valueOf(id);
		}

		title = StringUtil.toLowerCase(title.trim());

		if (Validator.isNull(title) || Validator.isNumber(title) ||
			title.equals("rss")) {

			title = String.valueOf(id);
		}
		else {
			title = FriendlyURLNormalizerUtil.normalizeWithEncoding(title);
		}

		if (title.contains(StringPool.PERIOD)) {
			title = title.replace(CharPool.PERIOD, CharPool.DASH);
		}

		if (title.contains(StringPool.SLASH)) {
			title = title.replace(CharPool.SLASH, CharPool.DASH);
		}

		return ModelHintsUtil.trimString(
			JournalArticle.class.getName(), "urlTitle", title);
	}

	protected void clearURLTitle() throws Exception {
		PreparedStatement ps = null;

		try {
			String sql =
				"update JournalArticle set urlTitle = ? where groupId = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, StringPool.BLANK);
			ps.setLong(2, OSBCustomerConstants.GROUP_CUSTOMER_ID);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		clearURLTitle();
		updateJournalArticle();

		_journalArticlePersistence.clearCache();
	}

	protected String getUniqueUrlTitle(
			long groupId, String articleId, String urlTitle)
		throws Exception {

		String normalizedUrlTitle = FriendlyURLNormalizerUtil.normalize(
			urlTitle);

		int maxLength = ModelHintsUtil.getMaxLength(
			JournalArticle.class.getName(), "urlTitle");

		String curUrlTitle = normalizedUrlTitle.substring(
			0, Math.min(maxLength, normalizedUrlTitle.length()));

		for (int i = 1;; i++) {
			JournalArticle article =
				_journalArticleLocalService.fetchArticleByUrlTitle(
					groupId, curUrlTitle);

			if ((article == null) || articleId.equals(article.getArticleId())) {
				break;
			}

			String suffix = StringPool.DASH + i;

			String prefix = normalizedUrlTitle.substring(
				0,
				Math.min(
					maxLength - suffix.length(), normalizedUrlTitle.length()));

			curUrlTitle = prefix + suffix;
		}

		return curUrlTitle;
	}

	protected void updateJournalArticle() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select distinct articleId from JournalArticle where groupId " +
					"= ?";

			ps = connection.prepareStatement(sql);

			long groupId = OSBCustomerConstants.GROUP_CUSTOMER_ID;

			ps.setLong(1, groupId);

			rs = ps.executeQuery();

			while (rs.next()) {
				String articleId = rs.getString("articleId");

				JournalArticle article =
					_journalArticleLocalService.getLatestArticle(
						groupId, articleId);

				String urlTitle = getUrlTitle(
					article.getId(), article.getTitle(LocaleUtil.US));

				urlTitle = getUniqueUrlTitle(groupId, articleId, urlTitle);

				updateUrlTitles(groupId, articleId, urlTitle);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateUrlTitles(
			long groupId, String articleId, String urlTitle)
		throws Exception {

		List<JournalArticle> articles = _journalArticleLocalService.getArticles(
			groupId, articleId);

		for (JournalArticle curArticle : articles) {
			curArticle.setUrlTitle(urlTitle);

			_journalArticleLocalService.updateJournalArticle(curArticle);
		}
	}

	private final JournalArticleLocalService _journalArticleLocalService;
	private final JournalArticlePersistence _journalArticlePersistence;

}