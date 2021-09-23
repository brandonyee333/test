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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rachael Koestartyo
 */
@GraphQLType("individual")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("individuals")
public class ReportIndividualDTO {

	public ReportIndividualDTO() {
	}

	public ReportIndividualDTO(Individual individual) {
		_custom = _getIndividualProperties(individual.getCustomDemographics());
		_demographics = _getIndividualProperties(individual.getDemographics());
		_id = StringUtil.get(individual.getId());
		_individualSegmentIds = ListUtil.map(
			individual.getSegmentIds(), String::valueOf);
	}

	public Map<String, String> getCustom() {
		return _custom;
	}

	public Map<String, String> getDemographics() {
		return _demographics;
	}

	@GraphQLProperty("email")
	@JsonIgnore
	public String getEmailAddress() {
		return _demographics.get("email");
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
		return _demographics.get("name");
	}

	public void setCustom(Map<String, String> custom) {
		_custom = custom;
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

	private Map<String, String> _getIndividualProperties(
		Individual.Demographics demographics) {

		if (demographics == null) {
			return Collections.emptyMap();
		}

		return FieldDog.toMap(demographics.getFields());
	}

	private Map<String, String> _custom = new HashMap<>();
	private Map<String, String> _demographics = new HashMap<>();
	private String _id;
	private List<String> _individualSegmentIds;

}