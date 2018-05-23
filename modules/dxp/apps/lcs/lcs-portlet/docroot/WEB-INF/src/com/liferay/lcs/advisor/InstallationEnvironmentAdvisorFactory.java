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

package com.liferay.lcs.advisor;

import com.liferay.portal.kernel.util.OSDetector;

/**
 * @author Igor Beslic
 */
public class InstallationEnvironmentAdvisorFactory {

	public static InstallationEnvironmentAdvisor getInstance() {
		if (_installationEnvironmentAdvisor != null) {
			return _installationEnvironmentAdvisor;
		}

		if (OSDetector.isAIX()) {
			_installationEnvironmentAdvisor =
				new AIXInstallationEnvironmentAdvisor();
		}
		else if (OSDetector.isLinux()) {
			_installationEnvironmentAdvisor =
				new LinuxInstallationEnvironmentAdvisor();
		}
		else {
			_installationEnvironmentAdvisor =
				new DefaultInstallationEnvironmentAdvisor();
		}

		return _installationEnvironmentAdvisor;
	}

	private static InstallationEnvironmentAdvisor
		_installationEnvironmentAdvisor;

}