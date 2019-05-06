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

package com.liferay.osb.customer.release.tool.web.internal.search;

import com.liferay.osb.customer.release.tool.web.internal.exception.VersionRangeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseSearcher {

	public JSONObject search(
		PortletRequest portletRequest, MimeResponse mimeResponse) {

		try {
			double fromProductVersion = ParamUtil.getDouble(
				portletRequest, "fromProductVersion");

			if (fromProductVersion <= 0) {
				return emptySearch();
			}

			double fromFixPackVersion = ParamUtil.getDouble(
				portletRequest, "fromFixPackVersion");
			double toFixPackVersion = ParamUtil.getDouble(
				portletRequest, "toFixPackVersion");

			validateQueryRange(fromFixPackVersion, toFixPackVersion);

			return doSearch(portletRequest, mimeResponse);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			HttpServletRequest request = portal.getHttpServletRequest(
				portletRequest);

			JSONObject errorJSONObject = jsonFactory.createJSONObject();

			String message = StringPool.BLANK;

			if (e instanceof VersionRangeException) {
				message = LanguageUtil.get(
					request, "please-enter-a-valid-version-range");
			}
			else {
				message = LanguageUtil.get(
					request, "an-unexpected-error-occurred");
			}

			errorJSONObject.put("message", message);

			errorJSONObject.put("name", e.getClass());

			JSONObject jsonObject = jsonFactory.createJSONObject();

			jsonObject.put("error", errorJSONObject);

			return jsonObject;
		}
	}

	protected abstract JSONObject doSearch(
			PortletRequest portletRequest, MimeResponse mimeResponse)
		throws Exception;

	protected JSONObject emptySearch() {
		JSONObject jsonObject = jsonFactory.createJSONObject();

		jsonObject.put("results", jsonFactory.createJSONArray());
		jsonObject.put("total", 0);

		return jsonObject;
	}

	protected void validateQueryRange(
			double fromFixPackVersion, double toFixPackVersion)
		throws PortalException {

		if (fromFixPackVersion > toFixPackVersion) {
			throw new VersionRangeException();
		}
	}

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected Portal portal;

	private static final Log _log = LogFactoryUtil.getLog(BaseSearcher.class);

}