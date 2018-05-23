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