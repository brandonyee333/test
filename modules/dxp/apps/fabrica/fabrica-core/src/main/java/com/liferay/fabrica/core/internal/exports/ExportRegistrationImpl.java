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

package com.liferay.fabrica.core.internal.exports;

import com.liferay.fabrica.core.admin.ExtendedRemoteServiceAdmin;

import java.util.Map;

import org.osgi.service.remoteserviceadmin.EndpointDescription;
import org.osgi.service.remoteserviceadmin.ExportReference;
import org.osgi.service.remoteserviceadmin.ExportRegistration;

/**
 * @author Miguel Pastor
 */
public class ExportRegistrationImpl implements ExportRegistration {

	public ExportRegistrationImpl(
		ExportReference exportReference,
		ExtendedRemoteServiceAdmin extendedRemoteServiceAdmin) {

		_exportReference = exportReference;
		_extendedRemoteServiceAdmin = extendedRemoteServiceAdmin;
	}

	@Override
	public void close() {
		_extendedRemoteServiceAdmin.removeExportedService(_exportReference);
	}

	@Override
	public Throwable getException() {
		return null;
	}

	@Override
	public ExportReference getExportReference() {
		return _exportReference;
	}

	@Override
	public EndpointDescription update(Map<String, ?> properties) {
		return _exportReference.getExportedEndpoint();
	}

	private final ExportReference _exportReference;
	private final ExtendedRemoteServiceAdmin _extendedRemoteServiceAdmin;

}