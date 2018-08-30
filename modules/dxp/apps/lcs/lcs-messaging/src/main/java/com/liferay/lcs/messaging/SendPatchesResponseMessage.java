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
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SendPatchesResponseMessage extends ResponseMessage {

	public List<String> getFixedIssues() {
		return _fixedIssues;
	}

	public String getHashCode() {
		return _hashCode;
	}

	public Map<String, Integer> getPatchIdsStatuses() {
		return _patchIdsStatuses;
	}

	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	public boolean isPatchingToolEnabled() {
		return _patchingToolEnabled;
	}

	public void setFixedIssues(List<String> fixedIssues) {
		_fixedIssues = fixedIssues;
	}

	public void setHashCode(String hashCode) {
		_hashCode = hashCode;
	}

	public void setPatchIdsStatuses(Map<String, Integer> patchIdsStatuses) {
		_patchIdsStatuses = patchIdsStatuses;
	}

	public void setPatchingToolEnabled(boolean patchingToolEnabled) {
		_patchingToolEnabled = patchingToolEnabled;
	}

	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	private List<String> _fixedIssues = new ArrayList<String>();
	private String _hashCode;
	private Map<String, Integer> _patchIdsStatuses =
		new HashMap<String, Integer>();
	private boolean _patchingToolEnabled;
	private int _patchingToolVersion;

}