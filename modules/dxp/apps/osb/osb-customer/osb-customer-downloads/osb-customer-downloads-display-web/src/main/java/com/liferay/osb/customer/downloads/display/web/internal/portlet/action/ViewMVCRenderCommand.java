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

package com.liferay.osb.customer.downloads.display.web.internal.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryConstants;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayWebKeys;
import com.liferay.osb.customer.downloads.display.web.internal.util.DownloadsAssetCategoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			PortletPreferences portletPreferences =
				renderRequest.getPreferences();

			String ddmStructureKey = portletPreferences.getValue(
				"ddmStructureKey", null);

			if (Validator.isNull(ddmStructureKey)) {
				renderRequest.setAttribute(
					WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);

				return "/portlet_not_setup.jsp";
			}

			return doRender(renderRequest, renderResponse);
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());

			return "/error.jsp";
		}
	}

	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		long journalArticleResourcePrimKey = ParamUtil.getLong(
			renderRequest, "journalArticleResourcePrimKey");

		if (journalArticleResourcePrimKey > 0) {
			JournalArticle journalArticle =
				_journalArticleService.getLatestArticle(
					journalArticleResourcePrimKey);

			renderRequest.setAttribute(
				DownloadsDisplayWebKeys.JOURNAL_ARTICLE, journalArticle);

			renderRequest.setAttribute(
				DownloadsDisplayWebKeys.ASSET_CATEGORY_PRODUCT,
				_downloadsAssetCategoryUtil.getProductAssetCategory(
					journalArticleResourcePrimKey));

			return "/view_download.jsp";
		}
		else {
			AssetCategory productAssetCategory = getProductAssetCategory(
				renderRequest);

			if (productAssetCategory != null) {
				renderRequest.setAttribute(
					DownloadsDisplayWebKeys.ASSET_CATEGORY_PRODUCT,
					productAssetCategory);

				AssetCategory fileTypeAssetCategory = getFileTypeAssetCategory(
					renderRequest, productAssetCategory.getCategoryId());

				if (fileTypeAssetCategory != null) {
					renderRequest.setAttribute(
						DownloadsDisplayWebKeys.ASSET_CATEGORY_FILE_TYPE,
						fileTypeAssetCategory);
				}
			}

			return "/view.jsp";
		}
	}

	protected AssetCategory getFileTypeAssetCategory(
		RenderRequest renderRequest, long productAssetCategoryId) {

		long fileTypeAssetCategoryId = ParamUtil.getLong(
			renderRequest, "fileTypeAssetCategoryId");
		String fileType = ParamUtil.getString(renderRequest, "fileType");

		AssetCategory assetCategory = null;

		if (fileTypeAssetCategoryId > 0) {
			assetCategory = _assetCategoryLocalService.fetchAssetCategory(
				fileTypeAssetCategoryId);
		}

		if ((assetCategory == null) && Validator.isNotNull(fileType)) {
			assetCategory = _downloadsAssetCategoryUtil.getAssetCategory(
				productAssetCategoryId, fileType);
		}

		return assetCategory;
	}

	protected AssetCategory getProductAssetCategory(
		RenderRequest renderRequest) {

		long productAssetCategoryId = ParamUtil.getLong(
			renderRequest, "productAssetCategoryId");
		String product = ParamUtil.getString(renderRequest, "product");

		AssetCategory assetCategory = null;

		if (productAssetCategoryId > 0) {
			assetCategory = _assetCategoryLocalService.fetchAssetCategory(
				productAssetCategoryId);
		}

		if ((assetCategory == null) && Validator.isNotNull(product)) {
			assetCategory = _downloadsAssetCategoryUtil.getAssetCategory(
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, product);
		}

		return assetCategory;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private DownloadsAssetCategoryUtil _downloadsAssetCategoryUtil;

	@Reference
	private JournalArticleService _journalArticleService;

}