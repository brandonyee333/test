/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.single.internal;

import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.util.MethodHandler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	enabled = false, immediate = true, service = ClusterMasterExecutor.class
)
public class SingleClusterMasterExecutor implements ClusterMasterExecutor {

	@Override
	public void addClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {
	}

	@Override
	public <T> NoticeableFuture<T> executeOnMaster(
		MethodHandler methodHandler) {

		return null;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public boolean isMaster() {
		return true;
	}

	@Override
	public void removeClusterMasterTokenTransitionListener(
		ClusterMasterTokenTransitionListener
			clusterMasterTokenTransitionListener) {
	}

}