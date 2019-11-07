/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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