/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.advisor;

import com.liferay.lcs.client.advisor.ClusterNodeAdvisor;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = ClusterNodeAdvisor.class)
public class ClusterNodeAdvisorImpl implements ClusterNodeAdvisor {

	@Override
	public List<Map<String, Object>> getClusterNodeInfos() {
		if (!_clusterExecutor.isEnabled()) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> clusterNodeInfos = new ArrayList<>();

		ClusterNode localClusterNode = _clusterExecutor.getLocalClusterNode();

		String localClusterNodeId = localClusterNode.getClusterNodeId();

		List<ClusterNode> clusterNodes = _clusterExecutor.getClusterNodes();

		for (ClusterNode clusterNode : clusterNodes) {
			String clusterNodeId = clusterNode.getClusterNodeId();

			if (clusterNodeId.equals(localClusterNodeId)) {
				if (_log.isTraceEnabled()) {
					_log.trace(
						"Skipped local cluster node " + localClusterNodeId);
				}

				continue;
			}

			Map<String, Object> clusterNodeInfo = new HashMap<>();

			InetAddress inetAddress = clusterNode.getBindInetAddress();

			clusterNodeInfo.put("address", inetAddress.getHostAddress());

			clusterNodeInfo.put("clusterNodeId", clusterNodeId);

			String key = _getClusterNodeKey(clusterNodeId);

			if (key != null) {
				clusterNodeInfo.put("key", key);
			}

			clusterNodeInfos.add(clusterNodeInfo);

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Added cluster node info " +
						MapUtil.toString(clusterNodeInfo));
			}
		}

		return clusterNodeInfos;
	}

	@Override
	public List<String> getClusterNodeKeys() {
		if (!_clusterExecutor.isEnabled()) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> clusterNodeInfos = getClusterNodeInfos();

		List<String> clusterNodeKeys = new ArrayList<>();

		for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
			clusterNodeKeys.add((String)clusterNodeInfo.get("key"));
		}

		return clusterNodeKeys;
	}

	@Override
	public String getLocalClusterNodeAddress() {
		if (!_clusterExecutor.isEnabled()) {
			return null;
		}

		ClusterNode localClusterNode = _clusterExecutor.getLocalClusterNode();

		InetAddress inetAddress = localClusterNode.getBindInetAddress();

		return inetAddress.getHostAddress();
	}

	private static BundleContext _getBundleContext() {
		Bundle bundle = FrameworkUtil.getBundle(ClusterNodeAdvisorImpl.class);

		return bundle.getBundleContext();
	}

	private static String _getClusterNodeKey() {
		try {
			BundleContext bundleContext = _getBundleContext();

			ServiceReference<LCSKeyAdvisor> lcsKeyAdvisorServiceReference =
				bundleContext.getServiceReference(LCSKeyAdvisor.class);

			LCSKeyAdvisor lcsKeyAdvisor = bundleContext.getService(
				lcsKeyAdvisorServiceReference);

			try {
				String key = lcsKeyAdvisor.getKey();

				if (_log.isTraceEnabled()) {
					_log.trace("Cluster node key " + key);
				}

				return key;
			}
			finally {
				bundleContext.ungetService(lcsKeyAdvisorServiceReference);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	private String _getClusterNodeKey(String clusterNodeId) {
		if (_log.isTraceEnabled()) {
			_log.trace(
				"Invoking cluster request for getClusterInfo method for " +
					"cluster node ID " + clusterNodeId);
		}

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			_getClusterNodeKeyMethodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			_clusterExecutor.execute(clusterRequest);

		String key = null;

		try {
			ClusterNodeResponses clusterNodeResponses =
				futureClusterResponses.get(20000, TimeUnit.MILLISECONDS);

			ClusterNodeResponse clusterNodeResponse =
				clusterNodeResponses.getClusterResponse(clusterNodeId);

			key = (String)clusterNodeResponse.getResult();

			if (_log.isTraceEnabled()) {
				_log.trace(
					"getClusterNodeKey method handler invocation returned " +
						key);
			}
		}
		catch (Exception e) {
			_log.error(
				"Unable to complete cluster request for node " + clusterNodeId);
		}

		return key;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClusterNodeAdvisorImpl.class);

	private static final MethodHandler _getClusterNodeKeyMethodHandler =
		new MethodHandler(
			new MethodKey(ClusterNodeAdvisorImpl.class, "_getClusterNodeKey"));

	@Reference
	private ClusterExecutor _clusterExecutor;

}