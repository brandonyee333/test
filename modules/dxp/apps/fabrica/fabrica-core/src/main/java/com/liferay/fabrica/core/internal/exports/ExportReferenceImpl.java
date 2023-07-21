/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.core.internal.exports;

import org.osgi.framework.ServiceReference;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ExportReference;

/**
 * @author Miguel Pastor
 */
public class ExportReferenceImpl implements ExportReference {

	public ExportReferenceImpl(
		EndpointDescription endpointDescription,
		ServiceReference<?> serviceReference) {

		_endpointDescription = endpointDescription;
		_serviceReference = serviceReference;
	}

	@Override
	public EndpointDescription getExportedEndpoint() {
		return _endpointDescription;
	}

	@Override
	public ServiceReference<?> getExportedService() {
		return _serviceReference;
	}

	private final EndpointDescription _endpointDescription;
	private final ServiceReference<?> _serviceReference;

}