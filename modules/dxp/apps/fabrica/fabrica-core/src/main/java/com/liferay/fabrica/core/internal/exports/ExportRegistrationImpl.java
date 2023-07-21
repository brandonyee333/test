/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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