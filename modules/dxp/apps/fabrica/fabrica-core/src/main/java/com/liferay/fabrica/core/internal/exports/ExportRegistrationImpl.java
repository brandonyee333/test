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