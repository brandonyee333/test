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