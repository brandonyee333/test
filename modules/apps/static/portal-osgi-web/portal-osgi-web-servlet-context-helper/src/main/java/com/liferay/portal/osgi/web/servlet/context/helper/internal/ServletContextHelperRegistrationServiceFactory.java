/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.servlet.context.helper.internal;

import com.liferay.portal.osgi.web.servlet.context.helper.ServletContextHelperRegistration;

import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.apache.felix.utils.log.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Raymond Augé
 */
public class ServletContextHelperRegistrationServiceFactory
	implements ServiceFactory<ServletContextHelperRegistration> {

	public ServletContextHelperRegistrationServiceFactory(
		SAXParserFactory saxParserFactory, Logger logger,
		Map<String, Object> properties) {

		_saxParserFactory = saxParserFactory;
		_logger = logger;
		_properties = properties;
	}

	@Override
	public ServletContextHelperRegistration getService(
		Bundle bundle,
		ServiceRegistration<ServletContextHelperRegistration> registration) {

		return new ServletContextHelperRegistrationImpl(
			bundle, _saxParserFactory, _logger, _properties);
	}

	@Override
	public void ungetService(
		Bundle bundle,
		ServiceRegistration<ServletContextHelperRegistration> registration,
		ServletContextHelperRegistration servletContextHelperRegistration) {

		servletContextHelperRegistration.close();
	}

	private final Logger _logger;
	private final Map<String, Object> _properties;
	private final SAXParserFactory _saxParserFactory;

}