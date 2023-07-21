/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.service.impl;

import com.liferay.journal.exception.DuplicateArticleImageIdException;
import com.liferay.journal.model.JournalArticleImage;
import com.liferay.journal.service.base.JournalArticleImageLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalArticleImageLocalServiceImpl
	extends JournalArticleImageLocalServiceBaseImpl {

	@Override
	public void addArticleImageId(
			long articleImageId, long groupId, String articleId, double version,
			String elInstanceId, String elName, String languageId)
		throws PortalException {

		if (articleImageId <= 0) {
			return;
		}

		JournalArticleImage articleImage =
			journalArticleImagePersistence.fetchByG_A_V_E_E_L(
				groupId, articleId, version, elInstanceId, elName, languageId);

		if (articleImage == null) {
			articleImage = journalArticleImagePersistence.create(
				articleImageId);

			articleImage.setGroupId(groupId);
			articleImage.setArticleId(articleId);
			articleImage.setVersion(version);
			articleImage.setElInstanceId(elInstanceId);
			articleImage.setElName(elName);
			articleImage.setLanguageId(languageId);
			articleImage.setTempImage(false);

			articleImage = journalArticleImagePersistence.update(articleImage);
		}
		else if (articleImage.getArticleImageId() != articleImageId) {
			throw new DuplicateArticleImageIdException(
				"{articleImageId=" + articleImageId + "}");
		}
	}

	@Override
	public void deleteArticleImage(JournalArticleImage articleImage) {
		try {
			imageLocalService.deleteImage(articleImage.getArticleImageId());
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to delete image " +
						articleImage.getArticleImageId(),
					pe);
			}
		}

		journalArticleImagePersistence.remove(articleImage);
	}

	@Override
	public void deleteArticleImage(long articleImageId) {
		JournalArticleImage articleImage =
			journalArticleImagePersistence.fetchByPrimaryKey(articleImageId);

		if (articleImage != null) {
			deleteArticleImage(articleImage);
		}
	}

	@Override
	public void deleteArticleImage(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId) {

		JournalArticleImage articleImage =
			journalArticleImagePersistence.fetchByG_A_V_E_E_L(
				groupId, articleId, version, elInstanceId, elName, languageId);

		if (articleImage != null) {
			deleteArticleImage(articleImage);
		}
	}

	@Override
	public void deleteImages(long groupId, String articleId, double version) {
		for (JournalArticleImage articleImage :
				journalArticleImagePersistence.findByG_A_V(
					groupId, articleId, version)) {

			deleteArticleImage(articleImage);
		}
	}

	@Override
	public JournalArticleImage getArticleImage(long articleImageId)
		throws PortalException {

		return journalArticleImagePersistence.findByPrimaryKey(articleImageId);
	}

	@Override
	public long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId) {

		return getArticleImageId(
			groupId, articleId, version, elInstanceId, elName, languageId,
			false);
	}

	@Override
	public long getArticleImageId(
		long groupId, String articleId, double version, String elInstanceId,
		String elName, String languageId, boolean tempImage) {

		JournalArticleImage articleImage =
			journalArticleImagePersistence.fetchByG_A_V_E_E_L(
				groupId, articleId, version, elInstanceId, elName, languageId);

		if (articleImage == null) {
			long articleImageId = counterLocalService.increment();

			articleImage = journalArticleImagePersistence.create(
				articleImageId);

			articleImage.setGroupId(groupId);
			articleImage.setArticleId(articleId);
			articleImage.setVersion(version);
			articleImage.setElInstanceId(elInstanceId);
			articleImage.setElName(elName);
			articleImage.setLanguageId(languageId);
			articleImage.setTempImage(tempImage);

			articleImage = journalArticleImagePersistence.update(articleImage);
		}

		return articleImage.getArticleImageId();
	}

	@Override
	public List<JournalArticleImage> getArticleImages(long groupId) {
		return journalArticleImagePersistence.findByGroupId(groupId);
	}

	@Override
	public List<JournalArticleImage> getArticleImages(
		long groupId, String articleId, double version) {

		return journalArticleImagePersistence.findByG_A_V(
			groupId, articleId, version);
	}

	@Override
	public int getArticleImagesCount(long groupId) {
		return journalArticleImagePersistence.countByGroupId(groupId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleImageLocalServiceImpl.class);

}