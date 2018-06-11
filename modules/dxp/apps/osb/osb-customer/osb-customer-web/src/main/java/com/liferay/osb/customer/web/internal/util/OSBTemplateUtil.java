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

package com.liferay.osb.customer.web.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.osb.customer.asset.model.AssetCategoryDisplay;
import com.liferay.osb.customer.constants.OSBCustomerPortletKeys;
import com.liferay.osb.customer.web.internal.permission.OSBCustomerArticlePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(immediate = true, service = OSBTemplateUtil.class)
public class OSBTemplateUtil {

	public String getJournalArticleURL(
			long groupId, JournalArticle journalArticle)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		long plid = _portal.getPlidFromPortletId(
			groupId, OSBCustomerPortletKeys.DISPLAY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			serviceContext.getRequest(), OSBCustomerPortletKeys.DISPLAY, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/display/view.jsp");
		portletURL.setParameter("articleId", journalArticle.getArticleId());
		portletURL.setWindowState(LiferayWindowState.NORMAL);

		return portletURL.toString();
	}

	public String getPortraitURL(String imagePath, long userId) {
		try {
			User user = _userLocalService.fetchUser(userId);

			if ((user != null) && (user.getPortraitId() > 0)) {
				return UserConstants.getPortraitURL(
					imagePath, user.getMale(), user.getPortraitId(),
					user.getUserUuid());
			}
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	public boolean hasPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return OSBCustomerArticlePermission.contains(
			permissionChecker, actionId);
	}

	public String renderAssetCategories(
			Locale locale, String className, long classPK)
		throws PortalException {

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(className, classPK);

		AssetCategoryDisplay assetCategoryDisplay = new AssetCategoryDisplay(
			locale, assetCategories);

		return assetCategoryDisplay.renderAbstract();
	}

	@Reference(unbind = "-")
	protected void setAssetCategoryService(
		AssetCategoryService assetCategoryService) {

		_assetCategoryService = assetCategoryService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private AssetCategoryService _assetCategoryService;

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;

}