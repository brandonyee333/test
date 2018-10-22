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

package com.liferay.lcs.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Liferay Cloud Services Protocol handshake message. This message
 * is always sent from the LCS system to the LCS client. A successful handshake
 * results in a virtual session that lasts as long as the LCS system receives
 * heartbeat messages from the client.
 *
 * @author  Miguel Pastor
 * @author  Ivica Cardic
 * @author  Igor Beslic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public class HandshakeMessage extends Message {

	public Map<Integer, String> getCompanyIdsWebIds() {
		return _companyIdsWebIds;
	}

	public int getHashCode() {
		return _hashCode;
	}

	public long getHeartbeatInterval() {
		return _heartbeatInterval;
	}

	public long getLCSClusterEntryTokenId() {
		return _lcsClusterEntryTokenId;
	}

	public String getLCSClusterNodeName() {
		return _lcsClusterNodeName;
	}

	public int getLCSPortletBuildNumber() {
		return _lcsPortletBuildNumber;
	}

	public String getLCSPortletVersion() {
		return _lcsPortletVersion;
	}

	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	public int getPortalBuildNumber() {
		return _portalBuildNumber;
	}

	public String getPortalEdition() {
		return _portalEdition;
	}

	public int getProcessorCoresTotal() {
		return _processorCoresTotal;
	}

	public List<Map<String, Long>> getUptimes() {
		return _uptimes;
	}

	public boolean isClusterExecutorEnabled() {
		return _clusterExecutorEnabled;
	}

	public boolean isDeregister() {
		return _deregister;
	}

	/**
	 * @return
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public boolean isMetricsLCSServiceEnabled() {
		return _metricsLCSServiceEnabled;
	}

	public boolean isMonitoringEnabled() {
		return _monitoringEnabled;
	}

	/**
	 * @return
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public boolean isPatchesLCSServiceEnabled() {
		return _patchesLCSServiceEnabled;
	}

	public boolean isPatchingToolEnabled() {
		return _patchingToolEnabled;
	}

	/**
	 * @return
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public boolean isPortalPropertiesLCSServiceEnabled() {
		return _portalPropertiesLCSServiceEnabled;
	}

	public boolean isServerManuallyShutdown() {
		return _serverManuallyShutdown;
	}

	public boolean isSignOff() {
		return _signOff;
	}

	public void setClusterExecutorEnabled(boolean clusterExecutorEnabled) {
		_clusterExecutorEnabled = clusterExecutorEnabled;
	}

	public void setCompanyIdsWebIds(Map<Integer, String> companyIdsWebIds) {
		_companyIdsWebIds = companyIdsWebIds;
	}

	public void setDeregister(boolean deregister) {
		_deregister = deregister;
	}

	public void setHashCode(int hashCode) {
		_hashCode = hashCode;
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	public void setLCSClusterEntryTokenId(long lcsClusterEntryTokenId) {
		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
	}

	public void setLCSClusterNodeName(String lcsClusterNodeName) {
		_lcsClusterNodeName = lcsClusterNodeName;
	}

	public void setLCSPortletBuildNumber(int lcsPortletBuildNumber) {
		_lcsPortletBuildNumber = lcsPortletBuildNumber;
	}

	public void setLCSPortletVersion(String lcsPortletVersion) {
		_lcsPortletVersion = lcsPortletVersion;
	}

	/**
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public void setMetricsLCSServiceEnabled(boolean metricsLCSServiceEnabled) {
		_metricsLCSServiceEnabled = metricsLCSServiceEnabled;
	}

	public void setMonitoringEnabled(boolean monitoringEnabled) {
		_monitoringEnabled = monitoringEnabled;
	}

	/**
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public void setPatchesLCSServiceEnabled(boolean patchesLCSServiceEnabled) {
		_patchesLCSServiceEnabled = patchesLCSServiceEnabled;
	}

	public void setPatchingToolEnabled(boolean patchingToolEnabled) {
		_patchingToolEnabled = patchingToolEnabled;
	}

	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		_portalBuildNumber = portalBuildNumber;
	}

	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	/**
	 * @deprecated Will be removed in next version
	 */
	@Deprecated
	public void setPortalPropertiesLCSServiceEnabled(
		boolean portalPropertiesLCSServiceEnabled) {

		_portalPropertiesLCSServiceEnabled = portalPropertiesLCSServiceEnabled;
	}

	public void setProcessorCoresTotal(int processorCoresTotal) {
		_processorCoresTotal = processorCoresTotal;
	}

	public void setServerManuallyShutdown(boolean serverManuallyShutdown) {
		_serverManuallyShutdown = serverManuallyShutdown;
	}

	public void setSignOff(boolean signOff) {
		_signOff = signOff;
	}

	public void setUptimes(List<Map<String, Long>> uptimes) {
		_uptimes = uptimes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());

		sb.setLength(sb.length() - 1);

		sb.append(", lcsPortletBuildNumber=");
		sb.append(_lcsPortletBuildNumber);
		sb.append(", lcsPortletVersion=");
		sb.append(_lcsPortletVersion);
		sb.append(", patchingToolEnabled=");
		sb.append(_patchingToolEnabled);
		sb.append(", patchingToolVersion=");
		sb.append(_patchingToolVersion);
		sb.append(", portalBuildNumber=");
		sb.append(_portalBuildNumber);
		sb.append(", portalEdition=");
		sb.append(_portalEdition);
		sb.append(", processorCoresTotal=");
		sb.append(_processorCoresTotal);
		sb.append(", serverManuallyShutdown=");
		sb.append(_serverManuallyShutdown);
		sb.append("}");

		return sb.toString();
	}

	private boolean _clusterExecutorEnabled;
	private Map<Integer, String> _companyIdsWebIds =
		new HashMap<Integer, String>();
	private boolean _deregister;
	private int _hashCode;
	private long _heartbeatInterval;
	private long _lcsClusterEntryTokenId;
	private String _lcsClusterNodeName;
	private int _lcsPortletBuildNumber;
	private String _lcsPortletVersion;
	private boolean _metricsLCSServiceEnabled;
	private boolean _monitoringEnabled;
	private boolean _patchesLCSServiceEnabled;
	private boolean _patchingToolEnabled;
	private int _patchingToolVersion;
	private int _portalBuildNumber;
	private String _portalEdition;
	private boolean _portalPropertiesLCSServiceEnabled;
	private int _processorCoresTotal;
	private boolean _serverManuallyShutdown;
	private boolean _signOff;
	private List<Map<String, Long>> _uptimes =
		new ArrayList<Map<String, Long>>();

}