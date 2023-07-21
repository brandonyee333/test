/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.compatibility.ie.servlet.filter;

import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"servlet-context-name=",
		"servlet-filter-name=IE MimeType Compatibility Filter", "url-pattern=/*"
	},
	service = Filter.class
)
public class IEMimeTypeCompatibilityFilter extends BasePortalFilter {

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		processFilter(
			IEMimeTypeCompatibilityFilter.class.getName(), request,
			new IEMimeTypeCompatibilityResponseWrapper(request, response),
			filterChain);
	}

	private static class IEMimeTypeCompatibilityResponseWrapper
		extends HttpServletResponseWrapper {

		public IEMimeTypeCompatibilityResponseWrapper(
			HttpServletRequest request, HttpServletResponse response) {

			super(response);

			_request = request;
		}

		@Override
		public void setContentType(String contentType) {
			if (contentType.equals(ContentTypes.IMAGE_X_MS_BMP) &&
				BrowserSnifferUtil.isIe(_request)) {

				contentType = ContentTypes.IMAGE_BMP;
			}

			super.setContentType(contentType);
		}

		private final HttpServletRequest _request;

	}

}