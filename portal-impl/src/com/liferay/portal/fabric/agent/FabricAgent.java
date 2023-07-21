/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.agent;

import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.fabric.worker.FabricWorker;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessConfig;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.process.ProcessExecutor;

import java.io.Serializable;

import java.util.Collection;

/**
 * @author Shuyang Zhou
 */
public interface FabricAgent extends ProcessExecutor {

	@Override
	public <T extends Serializable> FabricWorker<T> execute(
			ProcessConfig processConfig, ProcessCallable<T> processCallable)
		throws ProcessException;

	public FabricStatus getFabricStatus();

	public Collection<? extends FabricWorker<?>> getFabricWorkers();

}