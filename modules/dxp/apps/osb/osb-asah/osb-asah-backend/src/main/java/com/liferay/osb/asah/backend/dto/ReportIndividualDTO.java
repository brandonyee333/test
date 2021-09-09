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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("individuals")
public class ReportIndividualDTO {

	public ReportIndividualDTO() {
	}

	public ReportIndividualDTO(Individual individual) {
		_id = individual.getId();
		_individualCustomFieldDTO = new IndividualFieldDTO(
			individual.getCustom());
		_individualFieldDTO = new IndividualFieldDTO(
			individual.getDemographics());
	}

	public ReportIndividualDTO(List<Individual> individuals) {
		_reportIndividualDTOs = SetUtil.map(
			individuals, ReportIndividualDTO::new);
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("custom")
	public IndividualFieldDTO getIndividualCustomFieldDTO() {
		return _individualCustomFieldDTO;
	}

	@JsonProperty("demographics")
	public IndividualFieldDTO getIndividualFieldDTO() {
		return _individualFieldDTO;
	}

	@JsonProperty("individuals")
	public Set<ReportIndividualDTO> getReportIndividualDTOs() {
		return _reportIndividualDTOs;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class IndividualFieldDTO {

		public IndividualFieldDTO(Map<String, String> fields) {
			_fieldMap = fields;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof IndividualFieldDTO)) {
				return false;
			}

			IndividualFieldDTO individualFieldDTO = (IndividualFieldDTO)obj;

			if (Objects.equals(_fieldMap, individualFieldDTO._fieldMap)) {
				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, String> getField() {
			return _fieldMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_fieldMap);
		}

		private final Map<String, String> _fieldMap;

	}

	private String _id;
	private IndividualFieldDTO _individualCustomFieldDTO;
	private IndividualFieldDTO _individualFieldDTO;
	private Set<ReportIndividualDTO> _reportIndividualDTOs;

}