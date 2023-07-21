/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.web.internal.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.osb.customer.release.tool.constants.ArtifactVersionConstants;
import com.liferay.osb.customer.release.tool.model.ArtifactVersionRange;
import com.liferay.osb.customer.release.tool.service.ArtifactVersionLocalService;
import com.liferay.osb.customer.release.tool.web.internal.constants.ProductConstants;
import com.liferay.osb.customer.release.tool.web.internal.util.ReleasesAssetCategoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.MimeResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(service = ArtifactVersionSearcher.class)
public class ArtifactVersionSearcher extends BaseSearcher {

	protected JSONObject doSearch(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws Exception {

		PortletPreferences preferences = portletRequest.getPreferences();

		String product = ParamUtil.getString(portletRequest, "product");

		double productVersion = ParamUtil.getDouble(
			portletRequest, "productVersion");

		double fromProductVersion = ParamUtil.getDouble(
			portletRequest, "fromProductVersion", productVersion);
		double toProductVersion = ParamUtil.getDouble(
			portletRequest, "toProductVersion", productVersion);

		double fromFixPackVersion = ParamUtil.getDouble(
			portletRequest, "fromFixPackVersion");
		double toFixPackVersion = ParamUtil.getDouble(
			portletRequest, "toFixPackVersion");
		String keywords = ParamUtil.getString(portletRequest, "keywords");
		int[] owners = ParamUtil.getIntegerValues(portletRequest, "owners");
		boolean changesOnly = ParamUtil.getBoolean(
			portletRequest, "changesOnly");

		AssetCategory fromProductAssetCategory =
			_releasesAssetCategoryUtil.getProductAssetCategory(
				product, fromProductVersion);

		AssetCategory fromFixPackAssetCategory =
			_releasesAssetCategoryUtil.getFixPackAssetCategory(
				fromProductAssetCategory.getCategoryId(), fromFixPackVersion);

		AssetCategory toProductAssetCategory =
			_releasesAssetCategoryUtil.getProductAssetCategory(
				product, toProductVersion);

		AssetCategory toFixPackAssetCategory =
			_releasesAssetCategoryUtil.getFixPackAssetCategory(
				toProductAssetCategory.getCategoryId(), toFixPackVersion);

		String productName = GetterUtil.getString(
			preferences.getValue("productName", null));

		if (productName.equals(ProductConstants.COMMERCE)) {
			owners = new int[] {ArtifactVersionConstants.OWNER_COMMERCE};
		}
		else if (owners.length <= 0) {
			owners = ArtifactVersionConstants.OWNERS_DXP;
		}

		List<ArtifactVersionRange> artifactVersionRanges =
			_artifactVersionLocalService.getArtifactVersionRanges(
				fromFixPackAssetCategory.getCategoryId(),
				toFixPackAssetCategory.getCategoryId(), owners, keywords,
				changesOnly);

		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put("total", artifactVersionRanges.size());

		JSONArray jsonArray = jsonFactory.createJSONArray();

		for (ArtifactVersionRange artifactVersionRange :
				artifactVersionRanges) {

			jsonArray.put(toJSONObject(artifactVersionRange));
		}

		jsonObject.put("results", jsonArray);

		return jsonObject;
	}

	protected JSONObject toJSONObject(ArtifactVersionRange artifactVersionRange)
		throws PortalException {

		JSONObject jsonObject = jsonFactory.createJSONObject();

		if (Validator.isNotNull(artifactVersionRange.getFromVersion())) {
			String fromRepositoryURL =
				ArtifactVersionConstants.getRepositoryURL(
					artifactVersionRange.getRepository(),
					artifactVersionRange.getGroup(),
					artifactVersionRange.getName(),
					artifactVersionRange.getFromVersion(),
					artifactVersionRange.getPackaging());

			jsonObject.put("fromRepositoryURL", fromRepositoryURL);

			jsonObject.put(
				"fromVersion", artifactVersionRange.getFromVersion());
		}
		else {
			jsonObject.put("fromVersion", StringPool.DASH);
		}

		jsonObject.put("group", artifactVersionRange.getGroup());
		jsonObject.put("name", artifactVersionRange.getName());

		if (Validator.isNotNull(artifactVersionRange.getToVersion())) {
			String toRepositoryURL = ArtifactVersionConstants.getRepositoryURL(
				artifactVersionRange.getRepository(),
				artifactVersionRange.getGroup(), artifactVersionRange.getName(),
				artifactVersionRange.getToVersion(),
				artifactVersionRange.getPackaging());

			jsonObject.put("toRepositoryURL", toRepositoryURL);

			jsonObject.put("toVersion", artifactVersionRange.getToVersion());
		}
		else {
			jsonObject.put("toVersion", StringPool.DASH);
		}

		return jsonObject;
	}

	@Reference
	private ArtifactVersionLocalService _artifactVersionLocalService;

	@Reference
	private ReleasesAssetCategoryUtil _releasesAssetCategoryUtil;

}