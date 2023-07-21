/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.servlet.WrapHttpServletRequestFilter;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * See https://issues.liferay.com/browse/LPS-18587.
 * </p>
 *
 * @author Michael C. Han
 */
public class CompoundSessionIdFilter
	extends BasePortalFilter implements WrapHttpServletRequestFilter {

	public CompoundSessionIdFilter() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			CompoundSessionIdServletRequestFactory.class);

		_serviceTracker.open();
	}

	@Override
	public void destroy() {
		_serviceTracker.close();

		super.destroy();
	}

	@Override
	public HttpServletRequest getWrappedHttpServletRequest(
		HttpServletRequest request, HttpServletResponse response) {

		CompoundSessionIdServletRequestFactory
			compoundSessionIdServletRequestFactory =
				_serviceTracker.getService();

		if (compoundSessionIdServletRequestFactory != null) {
			return compoundSessionIdServletRequestFactory.create(request);
		}

		return request;
	}

	@Override
	public boolean isFilterEnabled() {
		if (_serviceTracker.isEmpty()) {
			return false;
		}

		return true;
	}

	private final ServiceTracker
		<CompoundSessionIdServletRequestFactory,
		 CompoundSessionIdServletRequestFactory> _serviceTracker;

}