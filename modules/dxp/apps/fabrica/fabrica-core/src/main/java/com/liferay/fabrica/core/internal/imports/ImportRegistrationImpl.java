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