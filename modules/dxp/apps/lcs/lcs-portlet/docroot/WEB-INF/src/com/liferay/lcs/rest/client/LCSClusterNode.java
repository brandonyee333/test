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

package com.liferay.lcs.rest.client;

import java.util.Date;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNode {

	public boolean getArchived() {
		return _archived;
	}

	public int getBuildNumber() {
		return _buildNumber;
	}

	public Date getConfigurationModifiedDate() {
		return _configurationModifiedDate;
	}

	public String getDescription() {
		return _description;
	}

	public long getHeartbeatInterval() {
		return _heartbeatInterval;
	}

	public long getInstallationId() {
		return _installationId;
	}

	public String getKey() {
		return _key;
	}

	public long getLastHeartbeat() {
		return _lastHeartbeat;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
	}

	public String getLocation() {
		return _location;
	}

	public String getName() {
		return _name;
	}

	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	public String getPortalEdition() {
		return _portalEdition;
	}

	public String getProtocolVersion() {
		return _protocolVersion;
	}

	public int getStatus() {
		return _status;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	public void setConfigurationModifiedDate(Date configurationModifiedDate) {
		_configurationModifiedDate = configurationModifiedDate;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	public void setInstallationId(long installationId) {
		_installationId = installationId;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setLastHeartbeat(long lastHeartbeat) {
		_lastHeartbeat = lastHeartbeat;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeId = lcsClusterNodeId;
	}

	public void setLocation(String location) {
		_location = location;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	public void setProtocolVersion(String protocolVersion) {
		_protocolVersion = protocolVersion;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private boolean _archived;
	private int _buildNumber;
	private Date _configurationModifiedDate;
	private String _description;
	private long _heartbeatInterval;
	private long _installationId;
	private String _key;
	private long _lastHeartbeat;
	private long _lcsClusterEntryId;
	private long _lcsClusterNodeId;
	private String _location;
	private String _name;
	private int _patchingToolVersion;
	private String _portalEdition;
	private String _protocolVersion;
	private int _status;

}