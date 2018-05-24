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

import com.liferay.fabrica.core.admin.ExtendedRemoteServiceAdmin;

import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ImportReference;
import org.osgi.service.remoteserviceadmin.ImportRegistration;

/**
 * @author Miguel Pastor
 */
public class ImportRegistrationImpl implements ImportRegistration {

	public ImportRegistrationImpl(
		ImportReference importReference,
		ExtendedRemoteServiceAdmin extendedRemoteServiceAdmin) {

		_importReference = importReference;
		_extendedRemoteServiceAdmin = extendedRemoteServiceAdmin;
	}

	@Override
	public void close() {
		_extendedRemoteServiceAdmin.removeImportedService(_importReference);
	}

	@Override
	public Throwable getException() {
		return null;
	}

	@Override
	public ImportReference getImportReference() {
		return _importReference;
	}

	@Override
	public boolean update(EndpointDescription endpointDescription) {
		return false;
	}

	private final ExtendedRemoteServiceAdmin _extendedRemoteServiceAdmin;
	private final ImportReference _importReference;

}