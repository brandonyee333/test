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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("interest-terms")
public class InterestTopicDTO {

	public InterestTopicDTO() {
	}

	public InterestTopicDTO(List<String> interestTerms) {
		_interestTerms = interestTerms;
	}

	public InterestTopicDTO(String interestTerm) {
		_interestTerm = interestTerm;
	}

	@JsonProperty("term")
	public String getInterestTerm() {
		return _interestTerm;
	}

	@JsonProperty("interest-terms")
	public List<String> getInterestTerms() {
		return _interestTerms;
	}

	public void setInterestTerm(String interestTerm) {
		_interestTerm = interestTerm;
	}

	private String _interestTerm;
	private List<String> _interestTerms;

}