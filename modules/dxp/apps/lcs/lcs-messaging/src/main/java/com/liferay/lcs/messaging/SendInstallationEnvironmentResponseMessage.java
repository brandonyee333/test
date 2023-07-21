/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class SendInstallationEnvironmentResponseMessage
	extends ResponseMessage {

	public Map<String, String> getHardwareMetadata() {
		return _hardwareMetadata;
	}

	public Map<String, String> getSoftwareMetadata() {
		return _softwareMetadata;
	}

	public void setHardwareMetadata(Map<String, String> hardwareMetadata) {
		_hardwareMetadata = hardwareMetadata;
	}

	public void setSoftwareMetadata(Map<String, String> softwareMetadata) {
		_softwareMetadata = softwareMetadata;
	}

	private Map<String, String> _hardwareMetadata =
		new HashMap<String, String>();
	private Map<String, String> _softwareMetadata =
		new HashMap<String, String>();

}