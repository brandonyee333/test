/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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