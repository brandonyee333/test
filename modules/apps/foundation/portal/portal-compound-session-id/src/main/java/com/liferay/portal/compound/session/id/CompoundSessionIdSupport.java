/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.compound.session.id;

import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;
import com.liferay.portal.servlet.filters.compoundsessionid.CompoundSessionIdServletRequestFactory;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true)
public class CompoundSessionIdSupport {

	@Activate
	public void activate(BundleContext bundleContext) {
		if (!CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			return;
		}

		_serviceRegistration = bundleContext.registerService(
			CompoundSessionIdServletRequestFactory.class,
			new CompoundSessionIdServletRequestFactoryImpl(), null);
	}

	@Deactivate
	public void deactivate() {
		if (!CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			return;
		}

		_serviceRegistration.unregister();
	}

	private ServiceRegistration<CompoundSessionIdServletRequestFactory>
		_serviceRegistration;

}