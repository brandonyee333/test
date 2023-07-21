/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.single.internal;

import com.liferay.portal.kernel.cluster.ClusterEventListener;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;

import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tina Tian
 */
@Component(enabled = false, immediate = true, service = ClusterExecutor.class)
public class SingleClusterExecutor implements ClusterExecutor {

	@Override
	public void addClusterEventListener(
		ClusterEventListener clusterEventListener) {
	}

	@Override
	public FutureClusterResponses execute(ClusterRequest clusterRequest) {
		return null;
	}

	@Override
	public InetAddress getBindInetAddress() {
		return null;
	}

	@Override
	public NetworkInterface getBindNetworkInterface() {
		return null;
	}

	@Override
	public List<ClusterEventListener> getClusterEventListeners() {
		return Collections.emptyList();
	}

	@Override
	public List<ClusterNode> getClusterNodes() {
		return Collections.emptyList();
	}

	@Override
	public ClusterNode getLocalClusterNode() {
		return null;
	}

	@Override
	public boolean isClusterNodeAlive(String clusterNodeId) {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void removeClusterEventListener(
		ClusterEventListener clusterEventListener) {
	}

}