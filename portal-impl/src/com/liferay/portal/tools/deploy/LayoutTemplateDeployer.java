/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.model.Plugin;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.tools.ToolDependencies;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutTemplateDeployer extends BaseDeployer {

	public static void main(String[] args) {
		ToolDependencies.wireDeployers();

		List<String> wars = new ArrayList<>();
		List<String> jars = new ArrayList<>();

		for (String arg : args) {
			if (arg.endsWith(".war")) {
				wars.add(arg);
			}
			else if (arg.endsWith(".jar")) {
				jars.add(arg);
			}
		}

		StreamUtil.cleanUp(new LayoutTemplateDeployer(wars, jars));
	}

	public LayoutTemplateDeployer() {
	}

	public LayoutTemplateDeployer(List<String> wars, List<String> jars) {
		super(wars, jars);
	}

	@Override
	public String getPluginType() {
		return Plugin.TYPE_LAYOUT_TEMPLATE;
	}

}