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