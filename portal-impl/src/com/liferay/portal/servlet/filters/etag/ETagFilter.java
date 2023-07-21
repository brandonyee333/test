/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.etag;

import com.liferay.portal.kernel.servlet.RestrictedByteBufferCacheServletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PropsValues;

import java.nio.ByteBuffer;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class ETagFilter extends BasePortalFilter {

	public static final String SKIP_FILTER =
		ETagFilter.class.getName() + "#SKIP_FILTER";

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (ParamUtil.getBoolean(request, _ETAG, true) &&
			!isAlreadyFiltered(request)) {

			return true;
		}

		return false;
	}

	protected boolean isAlreadyFiltered(HttpServletRequest request) {
		if (request.getAttribute(SKIP_FILTER) != null) {
			return true;
		}

		return false;
	}

	protected boolean isEligibleForETag(int status) {
		if ((status >= HttpServletResponse.SC_OK) &&
			(status < HttpServletResponse.SC_MULTIPLE_CHOICES)) {

			return true;
		}

		return false;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		request.setAttribute(SKIP_FILTER, Boolean.TRUE);

		RestrictedByteBufferCacheServletResponse
			restrictedByteBufferCacheServletResponse =
				new RestrictedByteBufferCacheServletResponse(
					response, PropsValues.ETAG_RESPONSE_SIZE_MAX);

		processFilter(
			ETagFilter.class.getName(), request,
			restrictedByteBufferCacheServletResponse, filterChain);

		if (!restrictedByteBufferCacheServletResponse.isOverflowed()) {
			ByteBuffer byteBuffer =
				restrictedByteBufferCacheServletResponse.getByteBuffer();

			if (!isEligibleForETag(
					restrictedByteBufferCacheServletResponse.getStatus()) ||
				!ETagUtil.processETag(request, response, byteBuffer)) {

				restrictedByteBufferCacheServletResponse.flushCache();
			}
		}
	}

	private static final String _ETAG = "etag";

}