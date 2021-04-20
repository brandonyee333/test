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

import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransformationDTO {

	public TransformationDTO(
		String transformationKey, List<Transformation> transformations) {

		_transformationDTOMap = Collections.singletonMap(
			transformationKey,
			SetUtil.map(transformations, TransformationDTO::new));
	}

	public TransformationDTO(Transformation transformation) {
		_termDTO = new TermDTO(transformation.getTerm());
		_totalElements = transformation.getTotalElements();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TransformationDTO)) {
			return false;
		}

		TransformationDTO transformationDTO = (TransformationDTO)obj;

		if (Objects.equals(_termDTO, transformationDTO._termDTO) &&
			Objects.equals(_totalElements, transformationDTO._totalElements)) {

			return true;
		}

		return false;
	}

	@JsonProperty("terms")
	public TermDTO getTermDTO() {
		return _termDTO;
	}

	@JsonProperty("totalElements")
	public Integer getTotalElements() {
		return _totalElements;
	}

	@JsonAnyGetter
	public Map<String, Set<TransformationDTO>> getTransformationDTOMap() {
		return _transformationDTOMap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_termDTO, _totalElements);
	}

	public void setTermDTO(TermDTO termDTO) {
		_termDTO = termDTO;
	}

	public void setTotalElements(Integer totalElements) {
		_totalElements = totalElements;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class TermDTO {

		public TermDTO(Transformation.Term term) {
			_termsMap = term.getTermsMap();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof TermDTO)) {
				return false;
			}

			TermDTO termDTO = (TermDTO)obj;

			if (Objects.equals(_termsMap, termDTO._termsMap)) {
				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, Object> getTermsMap() {
			return _termsMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_termsMap);
		}

		public void setTermsMap(Map<String, Object> termsMap) {
			_termsMap = termsMap;
		}

		private Map<String, Object> _termsMap;

	}

	private TermDTO _termDTO;
	private Integer _totalElements;
	private Map<String, Set<TransformationDTO>> _transformationDTOMap =
		new HashMap<>();

}