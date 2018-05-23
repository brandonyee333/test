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

	public String getLocation() {
		return _location;
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

	public void setLocation(String location) {
		_location = location;
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
	private String _location;
	private String _name;
	private String _subscriptionType;
	private int _type;

}