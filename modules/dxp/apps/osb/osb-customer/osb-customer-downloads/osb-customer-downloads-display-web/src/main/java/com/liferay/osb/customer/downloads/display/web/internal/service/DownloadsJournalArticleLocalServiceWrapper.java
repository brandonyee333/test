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

package com.liferay.osb.customer.downloads.display.web.internal.service;

import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DDMStructureConstants;
import com.liferay.osb.customer.downloads.display.web.internal.util.JournalArticleUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class DownloadsJournalArticleLocalServiceWrapper
	extends JournalArticleLocalServiceWrapper {

	public DownloadsJournalArticleLocalServiceWrapper() {
		super(null);
	}

	public DownloadsJournalArticleLocalServiceWrapper(
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

		JournalArticle journalArticle = super.addArticle(
			userId, groupId, folderId, classNameId, classPK, articleId,
			autoArticleId, version, titleMap, descriptionMap, content,
			ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour,
			reviewDateMinute, neverReview, indexable, smallImage, smallImageURL,
			smallImageFile, images, articleURL, serviceContext);

		if (ArrayUtil.contains(DDMStructureConstants.KEYS, ddmStructureKey)) {
			updateResourcePermissions(journalArticle);
		}

		return journalArticle;
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

		JournalArticle journalArticle = super.updateArticle(
			userId, groupId, folderId, articleId, version, titleMap,
			descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
			reviewDateYear, reviewDateHour, reviewDateMinute, neverReview,
			indexable, smallImage, smallImageURL, smallImageFile, images,
			articleURL, serviceContext);

		if (ArrayUtil.contains(DDMStructureConstants.KEYS, ddmStructureKey)) {
			updateResourcePermissions(journalArticle);
		}

		return journalArticle;
	}

	protected Map<Long, String[]> getRoleIdsToActionIds(String product) {
		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_GUEST_ID, new String[0]);
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_LIFERAY_EMPLOYEE_ID,
			new String[] {ActionKeys.VIEW});
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_SITE_MEMBER_ID, new String[0]);

		if (Validator.isNull(product)) {
			return roleIdsToActionIds;
		}

		if (product.equals(DDMStructureConstants.PRODUCT_COMMERCE)) {
			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_COMMERCE_ID,
				new String[] {ActionKeys.VIEW});
		}
		else if (product.equals(
					DDMStructureConstants.PRODUCT_COMMERCE_CONNECTORS)) {

			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_COMMERCE_CONNECTORS_ID,
				new String[] {ActionKeys.VIEW});
		}
		else if (product.equals(DDMStructureConstants.PRODUCT_DXP_70) ||
				 product.equals(DDMStructureConstants.PRODUCT_DXP_71)) {

			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_DXP_ID,
				new String[] {ActionKeys.VIEW});
		}
		else if (product.equals(
					DDMStructureConstants.PRODUCT_ENTERPRISE_SEARCH_PREMIUM)) {

			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID,
				new String[] {ActionKeys.VIEW});
		}
		else if (product.equals(
					DDMStructureConstants.PRODUCT_ENTERPRISE_SEARCH_STANDARD)) {

			roleIdsToActionIds.put(
				OSBCustomerConstants.
					ROLE_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID,
				new String[] {ActionKeys.VIEW});
		}
		else if (product.equals(DDMStructureConstants.PRODUCT_PORTAL_52) ||
				 product.equals(DDMStructureConstants.PRODUCT_PORTAL_60) ||
				 product.equals(DDMStructureConstants.PRODUCT_PORTAL_61) ||
				 product.equals(DDMStructureConstants.PRODUCT_PORTAL_62)) {

			roleIdsToActionIds.put(
				OSBCustomerConstants.ROLE_CUSTOMER_PORTAL_ID,
				new String[] {ActionKeys.VIEW});
		}

		return roleIdsToActionIds;
	}

	protected Map<Long, String[]> getTrialRoleIdsToActionIds() {
		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_GUEST_ID, new String[0]);
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_LIFERAY_EMPLOYEE_ID,
			new String[] {ActionKeys.VIEW});
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_SITE_MEMBER_ID, new String[0]);
		roleIdsToActionIds.put(
			OSBCustomerConstants.ROLE_TRIAL_ID, new String[] {ActionKeys.VIEW});

		return roleIdsToActionIds;
	}

	protected void updateResourcePermissions(JournalArticle journalArticle)
		throws PortalException {

		String ddmStructureKey = journalArticle.getDDMStructureKey();

		Map<Long, String[]> roleIdsToActionIds = null;

		if (ddmStructureKey.equals(DDMStructureConstants.KEY_TRIAL_DOWNLOAD)) {
			roleIdsToActionIds = getTrialRoleIdsToActionIds();
		}
		else {
			Fields ddmFields = _journalConverter.getDDMFields(
				journalArticle.getDDMStructure(), journalArticle.getContent());

			Field ddmField = ddmFields.get("product");

			String product = JournalArticleUtil.getSelectOptionValue(ddmField);

			roleIdsToActionIds = getRoleIdsToActionIds(product);
		}

		_resourcePermissionLocalService.setResourcePermissions(
			journalArticle.getCompanyId(), JournalArticle.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(journalArticle.getResourcePrimKey()),
			roleIdsToActionIds);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalConverter _journalConverter;

	@Reference
	private Portal _portal;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}