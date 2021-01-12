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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.liferay.osb.asah.backend.graphql.GraphQLProperty;
import com.liferay.osb.asah.backend.graphql.GraphQLType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author André Miranda
 */
@GraphQLType
public class Individual {

	public Individual() {
	}

	public Individual(String emailAddress, String id, String name) {
		_emailAddress = emailAddress;
		_id = id;
		_name = name;
	}

	public Map<String, String> getDemographics() {
		return _demographics;
	}

	@GraphQLProperty("email")
	@JsonIgnore
	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getId() {
		return _id;
	}

	@JsonIgnore
	public List<String> getIndividualSegmentIds() {
		return _individualSegmentIds;
	}

	@JsonIgnore
	public String getName() {
		return _name;
	}

	public void setDemographics(Map<String, String> demographics) {
		_demographics = demographics;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIndividualSegmentIds(List<String> individualSegmentIds) {
		_individualSegmentIds = individualSegmentIds;
	}

	private Map<String, String> _demographics = new HashMap<>();
	private String _emailAddress;
	private String _id;
	private List<String> _individualSegmentIds;
	private String _name;

}