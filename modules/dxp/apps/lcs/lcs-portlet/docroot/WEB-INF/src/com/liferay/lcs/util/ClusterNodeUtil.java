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

package com.liferay.lcs.util;

import com.liferay.lcs.advisor.InstallationEnvironmentAdvisor;
import com.liferay.lcs.advisor.InstallationEnvironmentAdvisorFactory;
import com.liferay.lcs.rest.LCSClusterNodeClientUtil;
import com.liferay.lcs.rest.client.LCSClusterNode;
import com.liferay.lcs.rest.client.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.lcs.rest.client.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.lcs.rest.client.exception.RequiredLCSClusterNodeNameException;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.license.util.LicenseManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class ClusterNodeUtil {

	public static Map<String, Object> getClusterNodeInfo() {
		Map<String, Object> clusterNodeInfo = new HashMap<>();

		try {
			ClusterNode localClusterNode =
				ClusterExecutorUtil.getLocalClusterNode();

			if (_log.isTraceEnabled()) {
				_log.trace(
					"Executing getClusterInfo method for cluster node ID " +
						localClusterNode.getClusterNodeId());
			}

			InetAddress inetAddress = localClusterNode.getBindInetAddress();

			clusterNodeInfo.put("address", inetAddress.getHostAddress());

			clusterNodeInfo.put("key", _keyGenerator.getKey());

			if (LCSPortletPreferencesUtil.isCredentialsSet()) {
				LCSUtil.setUpJSONWebServiceClientCredentials();

				clusterNodeInfo.put(
					"registered", LCSUtil.isLCSClusterNodeRegistered());
			}
			else {
				clusterNodeInfo.put("registered", false);
			}

			clusterNodeInfo.put("ready", LCSConnectionManagerUtil.isReady());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Cluster node " + MapUtil.toString(clusterNodeInfo));
		}

		return clusterNodeInfo;
	}

	public static List<Map<String, Object>> getClusterNodeInfos()
		throws Exception {

		List<Map<String, Object>> clusterNodeInfos = new ArrayList<>();

		try {
			ClusterNode localClusterNode =
				ClusterExecutorUtil.getLocalClusterNode();

			String localClusterNodeId = localClusterNode.getClusterNodeId();

			List<ClusterNode> clusterNodes =
				ClusterExecutorUtil.getClusterNodes();

			for (ClusterNode clusterNode : clusterNodes) {
				String clusterNodeId = clusterNode.getClusterNodeId();

				if (clusterNodeId.equals(localClusterNodeId)) {
					continue;
				}

				Map<String, Object> clusterNodeInfo = new HashMap<>();

				if (_hasClusterNodeLCSPortletServletContext(clusterNodeId)) {
					clusterNodeInfo = _getClusterNodeInfo(clusterNodeId);
				}
				else {
					clusterNodeInfo.put("lcsPortletMissing", null);
					clusterNodeInfo.put("registered", false);
				}

				clusterNodeInfo.put("clusterNodeId", clusterNodeId);

				clusterNodeInfos.add(clusterNodeInfo);
			}
		}
		catch (ClassNotFoundException cnfe) {
			if (_log.isDebugEnabled()) {
				_log.debug(cnfe.getMessage(), cnfe);
			}
		}

		return clusterNodeInfos;
	}

	public static List<String> getRegisteredClusterNodeKeys() throws Exception {
		List<String> clusterNodeKeys = new ArrayList<>();

		if (!ClusterExecutorUtil.isEnabled()) {
			return clusterNodeKeys;
		}

		List<Map<String, Object>> clusterNodeInfos = getClusterNodeInfos();

		for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
			if (GetterUtil.getBoolean(clusterNodeInfo.get("registered"))) {
				clusterNodeKeys.add((String)clusterNodeInfo.get("key"));
			}
		}

		return clusterNodeKeys;
	}

	public static String registerClusterNode(long lcsClusterEntryId)
		throws DuplicateLCSClusterNodeNameException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException,
			   NoSuchLCSSubscriptionEntryException,
			   RequiredLCSClusterNodeNameException {

		InstallationEnvironmentAdvisor installationEnvironmentAdvisor =
			InstallationEnvironmentAdvisorFactory.getInstance();

		LCSClusterNode lcsClusterNode =
			LCSClusterNodeClientUtil.addLCSClusterNode(
				lcsClusterEntryId, _generateLCSClusterNodeName(),
				StringPool.BLANK, StringPool.BLANK,
				ReleaseInfo.getBuildNumber(),
				installationEnvironmentAdvisor.getProcessorCoresTotal());

		return lcsClusterNode.getKey();
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		_keyGenerator = keyGenerator;
	}

	private static String _generateLCSClusterNodeName() {
		return LicenseManagerUtil.getHostName() + StringPool.DASH +
			System.currentTimeMillis();
	}

	private static Map<String, Object> _getClusterNodeInfo(String clusterNodeId)
		throws Exception {

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Invoking cluster request for getClusterInfo method for " +
					"cluster node ID " + clusterNodeId);
		}

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			_getClusterNodeInfoMethodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		ClusterNodeResponses clusterNodeResponses = futureClusterResponses.get(
			20000, TimeUnit.MILLISECONDS);

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNodeId);

		Map<String, Object> result =
			(Map<String, Object>)clusterNodeResponse.getResult();

		if (_log.isTraceEnabled()) {
			_log.trace(
				"getClusterInfo method handler invocation returned " +
					MapUtil.toString(result));
		}

		return result;
	}

	private static boolean _hasClusterNodeLCSPortletServletContext(
			String clusterNodeId)
		throws Exception {

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Invoking cluster request for containsKey method handler for " +
					"cluster node ID " + clusterNodeId);
		}

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			_containsKeyMethodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		ClusterNodeResponses clusterNodeResponses = futureClusterResponses.get(
			20000, TimeUnit.MILLISECONDS);

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNodeId);

		boolean result = (Boolean)clusterNodeResponse.getResult();

		if (_log.isTraceEnabled()) {
			_log.trace(
				"containsKey method handler invocation returned " + result);
		}

		return result;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClusterNodeUtil.class);

	private static final MethodHandler _containsKeyMethodHandler =
		new MethodHandler(
			new MethodKey(
				ServletContextPool.class, "containsKey", String.class),
			"lcs-portlet");
	private static final MethodHandler _getClusterNodeInfoMethodHandler =
		new MethodHandler(
			new MethodKey(ClusterNodeUtil.class, "getClusterNodeInfo"));
	private static KeyGenerator _keyGenerator;

}