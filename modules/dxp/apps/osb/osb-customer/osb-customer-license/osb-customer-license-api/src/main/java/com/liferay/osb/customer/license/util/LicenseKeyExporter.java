/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.util;

import com.liferay.osb.customer.license.model.LicenseKey;

import java.io.File;

import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public interface LicenseKeyExporter {

	public String aggregateXMLs(String[] xmls) throws Exception;

	public String getFileName(LicenseKey licenseKey);

	public String toEncodedLicenseFile(LicenseKey licenseKey) throws Exception;

	public File toFile(LicenseKey licenseKey) throws Exception;

	public String toXML(LicenseKey licenseKey) throws Exception;

	public String toXML(List<LicenseKey> licenseKeys) throws Exception;

	public String toXML(Map<String, String> properties, String key)
		throws Exception;

}