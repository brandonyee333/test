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

package com.liferay.osb.customer.downloads.display.web.internal.servlet.filter;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsAssetCategoryConstants;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.customer.downloads.display.web.internal.util.DownloadsAssetCategoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Downloads Permission Filter",
		"url-pattern=/downloads/*", "url-pattern=/group/customer/downloads/*"
	},
	service = Filter.class
)
public class DownloadsPermissionFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			User user = _portal.getUser(request);

			if (user == null) {
				return false;
			}

			String portletId = ParamUtil.getString(request, "p_p_id");

			if (portletId.equals(
					DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY)) {

				String namespace = _portal.getPortletNamespace(portletId);

				long journalArticleResourcePrimKey = ParamUtil.getLong(
					request, namespace + "journalArticleResourcePrimKey");
				long productAssetCategoryId = ParamUtil.getLong(
					request, namespace + "productAssetCategoryId");
				String product = ParamUtil.getString(
					request, namespace + "product");

				if ((journalArticleResourcePrimKey > 0) ||
					(productAssetCategoryId > 0) ||
					Validator.isNotNull(product)) {

					return true;
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return false;
	}

	protected AssetCategory fetchProductAssetCategory(
			HttpServletRequest request)
		throws PortalException {

		String namespace = _portal.getPortletNamespace(
			DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY);

		long journalArticleResourcePrimKey = ParamUtil.getLong(
			request, namespace + "journalArticleResourcePrimKey");
		String product = ParamUtil.getString(request, namespace + "product");
		long productAssetCategoryId = ParamUtil.getLong(
			request, namespace + "productAssetCategoryId");

		AssetCategory assetCategory = null;

		if (journalArticleResourcePrimKey > 0) {
			assetCategory = _downloadsAssetCategoryUtil.getProductAssetCategory(
				journalArticleResourcePrimKey);
		}

		if ((assetCategory == null) && (productAssetCategoryId > 0)) {
			assetCategory = _assetCategoryLocalService.fetchCategory(
				productAssetCategoryId);
		}

		if ((assetCategory == null) && Validator.isNotNull(product)) {
			assetCategory = _downloadsAssetCategoryUtil.getAssetCategory(
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, product);
		}

		return assetCategory;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected String getRedirect(long assetCategoryId) throws PortalException {
		String propertyValue = _downloadsAssetCategoryUtil.getPropertyValue(
			assetCategoryId,
			DownloadsAssetCategoryConstants.PROPERTY_SUBSCRIPTION_PAGE_PLID);

		long plid = GetterUtil.getLong(propertyValue);

		if (plid > 0) {
			Layout layout = _layoutLocalService.getLayout(plid);

			return _portal.getLayoutActualURL(layout);
		}

		return null;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		AssetCategory assetCategory = fetchProductAssetCategory(request);

		if (assetCategory != null) {
			try {
				_assetCategoryService.getCategory(
					assetCategory.getCategoryId());
			}
			catch (PrincipalException pe) {
				String redirect = getRedirect(assetCategory.getCategoryId());

				if (Validator.isNotNull(redirect)) {
					response.sendRedirect(redirect);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadsPermissionFilter.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private DownloadsAssetCategoryUtil _downloadsAssetCategoryUtil;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}