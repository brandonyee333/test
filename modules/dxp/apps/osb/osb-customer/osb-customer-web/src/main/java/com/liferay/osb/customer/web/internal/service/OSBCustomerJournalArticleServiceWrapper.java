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
import com.liferay.osb.customer.constants.OSBCustomerConstants;
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
import com.liferay.portal.kernel.util.Portal;

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
public class OSBCustomerJournalArticleServiceWrapper
	extends JournalArticleLocalServiceWrapper {

	public OSBCustomerJournalArticleServiceWrapper() {
		super(null);
	}

	public OSBCustomerJournalArticleServiceWrapper(
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
				OSBCustomerConstants.DDM_STRUCTURE_ARTICLE_DISPLAY_KEY)) {

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
				OSBCustomerConstants.DDM_STRUCTURE_ARTICLE_DISPLAY_KEY)) {

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
			_portal.getSiteGroupId(groupId),
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

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}