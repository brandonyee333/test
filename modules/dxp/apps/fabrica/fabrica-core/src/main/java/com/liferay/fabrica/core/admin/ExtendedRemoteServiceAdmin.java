/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.core.admin;

import org.osgi.service.remoteserviceadmin.ExportReference;
import org.osgi.service.remoteserviceadmin.ImportReference;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdmin;

/**
 * @author Miguel Pastor
 */
public interface ExtendedRemoteServiceAdmin extends RemoteServiceAdmin {

	public void removeExportedService(ExportReference exportReference);

	public void removeImportedService(ImportReference importReference);

}