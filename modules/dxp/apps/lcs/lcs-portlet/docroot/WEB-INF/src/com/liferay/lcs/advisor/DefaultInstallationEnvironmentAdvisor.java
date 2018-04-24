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

import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;

/**
 * @author Igor Beslic
 */
public class DefaultInstallationEnvironmentAdvisor
	implements InstallationEnvironmentAdvisor {

	@Override
	public Map<String, String> getHardwareMetadata() {
		Map<String, String> hardwareMetadata = new HashMap<>();

		hardwareMetadata.putAll(getFilesystemMetadata());
		hardwareMetadata.putAll(getMemoryMetadata());
		hardwareMetadata.putAll(getProcessorMetadata());

		return hardwareMetadata;
	}

	@Override
	public int getProcessorCoresTotal() {
		Map<String, String> processorMetadata = getProcessorMetadata();

		return GetterUtil.getInteger(processorMetadata.get("cpu.total.cores"));
	}

	@Override
	public Map<String, String> getSoftwareMetadata() {
		Map<String, String> softwareMetadata = new HashMap<>();

		for (String key : _SYSTEM_PROPERTIES_SOFTWARE_KEYS) {
			String value = SystemProperties.get(key);

			if (Validator.isNotNull(value)) {
				softwareMetadata.put(key, value);
			}
		}

		softwareMetadata.put("appsrv.name", ServerDetector.getServerId());

		DB db = DBManagerUtil.getDB();

		DBType dbType = db.getDBType();

		softwareMetadata.put("database.name", dbType.getName());

		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		softwareMetadata.put(
			"java.input.arguments",
			StringUtil.merge(runtimeMXBean.getInputArguments()));

		softwareMetadata.put(
			"lcs.portlet.build.number",
			String.valueOf(LCSUtil.getLCSPortletBuildNumber()));
		softwareMetadata.put(
			"patching.tool.agent.present",
			String.valueOf(isPatchingToolAgentPresent()));

		return softwareMetadata;
	}

	protected Map<String, String> getFilesystemMetadata() {
		Map<String, String> map = new HashMap<>();

		File[] roots = File.listRoots();

		if (roots.length > 0) {
			map.put("fs.root", roots[0].getAbsolutePath());
			map.put(
				"fs.root.total.space",
				String.valueOf(roots[0].getTotalSpace()));
			map.put(
				"fs.root.usable.space",
				String.valueOf(roots[0].getUsableSpace()));
		}

		return map;
	}

	protected Map<String, String> getMemoryMetadata() {
		Map<String, String> map = new HashMap<>();

		Sigar sigar = new Sigar();

		try {
			Mem mem = sigar.getMem();

			map.put("physical.memory.free", String.valueOf(mem.getFree()));
			map.put("physical.memory.total", String.valueOf(mem.getTotal()));

			Swap swap = sigar.getSwap();

			map.put("swap.free", String.valueOf(swap.getFree()));
			map.put("swap.total", String.valueOf(swap.getTotal()));
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		finally {
			sigar.close();
		}

		return map;
	}

	protected Map<String, String> getProcessorMetadata() {
		Map<String, String> map = new HashMap<>();

		Sigar sigar = new Sigar();

		try {
			CpuInfo[] cpuInfos = sigar.getCpuInfoList();

			CpuInfo cpuInfo = cpuInfos[0];

			map.put("cpu.total.cores", String.valueOf(cpuInfo.getTotalCores()));
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
		finally {
			sigar.close();
		}

		return map;
	}

	protected boolean isPatchingToolAgentPresent() {
		Properties properties = System.getProperties();

		if (!properties.containsKey("env.JAVA_OPTS")) {
			return false;
		}

		String javaOpts = properties.getProperty("env.JAVA_OPTS");

		int index = javaOpts.indexOf("-javaagent");

		while ((index != -1) && (index < javaOpts.length())) {
			String option = StringPool.BLANK;

			if (javaOpts.indexOf(StringPool.SPACE, index) != -1) {
				option = javaOpts.substring(
					index, javaOpts.indexOf(StringPool.SPACE, index));
			}
			else {
				option = javaOpts.substring(index);
			}

			if (option.contains("patching-tool-agent.jar")) {
				return true;
			}

			index = javaOpts.indexOf("-javaagent", index + 10);
		}

		return false;
	}

	private static final String[] _SYSTEM_PROPERTIES_SOFTWARE_KEYS = {
		"file.encoding", "java.vendor", "java.version", "java.vm.name",
		"os.arch", "os.name", "os.version", "user.timezone"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultInstallationEnvironmentAdvisor.class);

}