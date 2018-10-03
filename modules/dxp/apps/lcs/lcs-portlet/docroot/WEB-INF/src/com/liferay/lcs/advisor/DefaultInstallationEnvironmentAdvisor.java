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

import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
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

/**
 * @author Igor Beslic
 */
public class DefaultInstallationEnvironmentAdvisor
	implements InstallationEnvironmentAdvisor {

	@Override
	public Map<String, String> getHardwareMetadata() {
		Map<String, String> hardwareMetadata = new HashMap<>();

		hardwareMetadata.putAll(getFileSystemMetadata());
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

	protected Map<String, String> getFileSystemMetadata() {
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

	protected Map<String, String> getProcessorMetadata() {
		Map<String, String> map = new HashMap<>();

		Runtime runtime = Runtime.getRuntime();

		map.put(
			"cpu.total.cores", String.valueOf(runtime.availableProcessors()));

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

}