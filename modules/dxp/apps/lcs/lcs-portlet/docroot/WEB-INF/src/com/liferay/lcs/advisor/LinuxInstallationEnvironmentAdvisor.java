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

import com.liferay.lcs.util.NProcCpuCount;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class LinuxInstallationEnvironmentAdvisor
	extends DefaultInstallationEnvironmentAdvisor {

	@Override
	protected Map<String, String> getProcessorMetadata() {
		Map<String, String> map = new HashMap<>();

		NProcCpuCount nProcCpuCount = new NProcCpuCount();

		try {
			map.put(
				"cpu.total.cores",
				String.valueOf(nProcCpuCount.getTotalCores()));
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}

		return map;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LinuxInstallationEnvironmentAdvisor.class);

}