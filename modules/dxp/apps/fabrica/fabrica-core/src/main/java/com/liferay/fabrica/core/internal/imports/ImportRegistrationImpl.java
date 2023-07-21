/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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