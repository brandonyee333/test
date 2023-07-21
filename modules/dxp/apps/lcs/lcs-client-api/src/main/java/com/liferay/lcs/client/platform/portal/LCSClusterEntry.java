/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSClusterEntry {

	public boolean getArchived() {
		return _archived;
	}

	public String getDescription() {
		return _description;
	}

	public boolean getElastic() {
		return _elastic;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public String getName() {
		return _name;
	}

	public String getSubscriptionType() {
		return _subscriptionType;
	}

	public int getType() {
		return _type;
	}

	public boolean isArchived() {
		return _archived;
	}

	public boolean isElastic() {
		return _elastic;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setElastic(boolean elastic) {
		_elastic = elastic;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setSubscriptionType(String subscriptionType) {
		_subscriptionType = subscriptionType;
	}

	public void setType(int type) {
		_type = type;
	}

	private boolean _archived;
	private String _description;
	private boolean _elastic;
	private long _lcsClusterEntryId;
	private long _lcsProjectId;
	private String _name;
	private String _subscriptionType;
	private int _type;

}