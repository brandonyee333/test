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