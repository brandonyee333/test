/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.image;

import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Timothy Bell
 */
public class LoopImageURL {

	public LoopImageURL(BaseModel<?> baseModel, String keyword, String type) {
		String imagePayload = BeanPropertiesUtil.getString(
			baseModel, "imagePayload");

		JSONObject imagePayloadJSONObject = null;

		try {
			imagePayloadJSONObject = JSONFactoryUtil.createJSONObject(
				imagePayload);
		}
		catch (JSONException jsone) {
			imagePayloadJSONObject = JSONFactoryUtil.createJSONObject();
		}

		this.imagePayloadJSONObject = imagePayloadJSONObject;

		this.keyword = keyword;
		this.type = type;
	}

	public String getImageURL() {
		String imageURL = StringPool.BLANK;

		JSONObject jsonObject = imagePayloadJSONObject.getJSONObject(keyword);

		if (jsonObject != null) {
			imageURL = jsonObject.getString("imageURL_" + type);
		}

		if (Validator.isNull(imageURL)) {
			imageURL = getPlaceholderImageURL();

			if (Validator.isNotNull(imageURL)) {
				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					PortalUtil.getDefaultCompanyId(), LoopPortletKeys.LOOP);

				imageURL = portlet.getContextPath() + imageURL;
			}
		}

		return imageURL;
	}

	protected String getPlaceholderImageURL() {
		return StringPool.BLANK;
	}

	protected final JSONObject imagePayloadJSONObject;
	protected final String keyword;
	protected final String type;

}