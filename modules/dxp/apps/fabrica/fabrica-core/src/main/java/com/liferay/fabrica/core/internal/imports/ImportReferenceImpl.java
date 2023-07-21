/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.core.internal.imports;

import org.osgi.framework.ServiceReference;
import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ImportReference;

/**
 * @author Miguel Pastor
 */
public class ImportReferenceImpl implements ImportReference {

	public ImportReferenceImpl(
		EndpointDescription endpointDescription,
		ServiceReference<?> serviceReference) {

		_endpointDescription = endpointDescription;
		_serviceReference = serviceReference;
	}

	@Override
	public EndpointDescription getImportedEndpoint() {
		return _endpointDescription;
	}

	@Override
	public ServiceReference<?> getImportedService() {
		return _serviceReference;
	}

	private final EndpointDescription _endpointDescription;
	private final ServiceReference<?> _serviceReference;

}