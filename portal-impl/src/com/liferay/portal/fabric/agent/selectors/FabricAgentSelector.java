/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent.selectors;

import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.kernel.process.ProcessCallable;

import java.util.Collection;

/**
 * @author Shuyang Zhou
 */
public interface FabricAgentSelector {

	public Collection<FabricAgent> select(
		Collection<FabricAgent> fabricAgents,
		ProcessCallable<?> processCallable);

}