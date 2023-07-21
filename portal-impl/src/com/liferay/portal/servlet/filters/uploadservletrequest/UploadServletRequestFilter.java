/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.uploadservletrequest;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.InvokerPortlet;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.PortletInstanceFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Preston Crary
 */
public class UploadServletRequestFilter extends BasePortalFilter {

	public static final String COPY_MULTIPART_STREAM_TO_FILE =
		UploadServletRequestFilter.class.getName() +
			"#COPY_MULTIPART_STREAM_TO_FILE";

	@Override
	public void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		UploadServletRequest uploadServletRequest = null;

		String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);

		if ((contentType != null) &&
			contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {

			String portletId = ParamUtil.getString(request, "p_p_id");

			if (Validator.isNotNull(portletId)) {
				long companyId = PortalUtil.getCompanyId(request);

				Portlet portlet = PortletLocalServiceUtil.getPortletById(
					companyId, portletId);

				if (portlet != null) {
					ServletContext servletContext =
						(ServletContext)request.getAttribute(WebKeys.CTX);

					InvokerPortlet invokerPortlet =
						PortletInstanceFactoryUtil.create(
							portlet, servletContext);

					LiferayPortletConfig liferayPortletConfig =
						(LiferayPortletConfig)invokerPortlet.getPortletConfig();

					if (invokerPortlet.isStrutsPortlet() ||
						liferayPortletConfig.isCopyRequestParameters() ||
						!liferayPortletConfig.isWARFile()) {

						request.setAttribute(
							UploadServletRequestFilter.
								COPY_MULTIPART_STREAM_TO_FILE,
							Boolean.FALSE);
					}
				}
			}

			uploadServletRequest = PortalUtil.getUploadServletRequest(request);
		}

		if (uploadServletRequest == null) {
			processFilter(
				UploadServletRequestFilter.class.getName(), request, response,
				filterChain);
		}
		else {
			try {
				processFilter(
					UploadServletRequestFilter.class.getName(),
					uploadServletRequest, response, filterChain);
			}
			finally {
				uploadServletRequest.cleanUp();
			}
		}
	}

}