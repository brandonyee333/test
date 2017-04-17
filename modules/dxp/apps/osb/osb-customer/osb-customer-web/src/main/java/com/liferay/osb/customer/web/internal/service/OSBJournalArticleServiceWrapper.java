/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.service;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 * @author John Zhao
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class OSBJournalArticleServiceWrapper
	extends JournalArticleLocalServiceWrapper {

	public OSBJournalArticleServiceWrapper() {
		super(null);
	}

	public OSBJournalArticleServiceWrapper(
		JournalArticleLocalService journalArticleLocalService) {

		super(journalArticleLocalService);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalArticle addArticle(
			long userId, long groupId, long folderId, long classNameId,
			long classPK, String articleId, boolean autoArticleId,
			double version, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String content,
			String ddmStructureKey, String ddmTemplateKey, String layoutUuid,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, boolean indexable, boolean smallImage,
			String smallImageURL, File smallImageFile,
			Map<String, byte[]> images, String articleURL,
			ServiceContext serviceContext)
		throws PortalException {

		JournalArticle article = super.addArticle(
			userId, groupId, folderId, classNameId, classPK, articleId,
			autoArticleId, version, titleMap, descriptionMap, content,
			ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
			reviewDateMinute, neverReview, indexable, smallImage, smallImageURL,
			smallImageFile, images, articleURL, serviceContext);

		if (ddmStructureKey.equals(
				OSBCustomerConstants.ARTICLE_DISPLAY_STRUCTURE_KEY)) {

			updateOriginalAuthor(article, serviceContext);

			updateResourcePermissions(
				groupId, ddmStructureKey, article.getResourcePrimKey(),
				serviceContext);
		}

		return article;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalArticle updateArticle(
			long userId, long groupId, long folderId, String articleId,
			double version, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String content,
			String ddmStructureKey, String ddmTemplateKey, String layoutUuid,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, boolean indexable, boolean smallImage,
			String smallImageURL, File smallImageFile,
			Map<String, byte[]> images, String articleURL,
			ServiceContext serviceContext)
		throws PortalException {

		JournalArticle article = super.updateArticle(
			userId, groupId, folderId, articleId, version, titleMap,
			descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
			reviewDateYear, reviewDateHour, reviewDateMinute, neverReview,
			indexable, smallImage, smallImageURL, smallImageFile, images,
			articleURL, serviceContext);

		if (ddmStructureKey.equals(
				OSBCustomerConstants.ARTICLE_DISPLAY_STRUCTURE_KEY)) {

			updateOriginalAuthor(article, serviceContext);

			updateResourcePermissions(
				groupId, ddmStructureKey, article.getResourcePrimKey(),
				serviceContext);
		}

		return article;
	}

	protected Map<Long, String[]> getRoleIdsToActionIds(boolean internal) {
		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

		if (internal) {
			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_ID, new String[0]);
			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_PARTNER_ID, new String[0]);
		}
		else {
			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_ID,
				new String[] {KBActionKeys.VIEW});
			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_PARTNER_ID,
				new String[] {KBActionKeys.VIEW});
		}

		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_GUEST_ID, new String[0]);
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_LIFERAY_EMPLOYEE_ID,
			new String[] {KBActionKeys.VIEW});
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_TRIAL_ID, new String[0]);

		return roleIdsToActionIds;
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourcePermissionLocalService(
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_resourcePermissionLocalService = resourcePermissionLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	protected void updateOriginalAuthor(
			JournalArticle article, ServiceContext serviceContext)
		throws PortalException {

		JournalArticle oldestArticle = getArticle(
			article.getGroupId(), article.getArticleId(), 1.0);

		long originalAuthorUserId = GetterUtil.getLong(
			serviceContext.getAttribute("originalAuthorUserId"));

		if (oldestArticle.getUserId() != originalAuthorUserId) {
			User user = _userLocalService.fetchUser(originalAuthorUserId);

			if (user != null) {
				oldestArticle.setUserId(originalAuthorUserId);
				oldestArticle.setUserName(user.getFullName());

				updateJournalArticle(oldestArticle);
			}
		}
	}

	protected void updateResourcePermissions(
			long groupId, String ddmStructureKey, long resourcePrimKey,
			ServiceContext serviceContext)
		throws PortalException {

		DDMStructure ddmStructure = _ddmStructureLocalService.getStructure(
			PortalUtil.getSiteGroupId(groupId),
			_classNameLocalService.getClassNameId(JournalArticle.class),
			ddmStructureKey, true);

		Fields fields = DDMUtil.getFields(
			ddmStructure.getStructureId(), serviceContext);

		Field field = fields.get("permissions");

		boolean internal = false;

		if (Objects.equals(field.getValue(), "[\"1\"]")) {
			internal = true;
		}

		Map<Long, String[]> roleIdsToActionIds = getRoleIdsToActionIds(
			internal);

		_resourcePermissionLocalService.setResourcePermissions(
			serviceContext.getCompanyId(), JournalArticle.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(resourcePrimKey),
			roleIdsToActionIds);
	}

	private ClassNameLocalService _classNameLocalService;
	private DDMStructureLocalService _ddmStructureLocalService;
	private ResourcePermissionLocalService _resourcePermissionLocalService;
	private UserLocalService _userLocalService;

}