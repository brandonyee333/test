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

package com.liferay.osb.asah.common.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Map;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDTO<T> {

	public PageDTO(
		String contentKey, T contentValue, Integer number, Integer size,
		Long totalElements, Integer totalPages) {

		_content = Collections.singletonMap(contentKey, contentValue);

		_page = new Page(number, size, totalElements, totalPages);
	}

	@JsonAnyGetter
	public Map<String, T> getContent() {
		return _content;
	}

	@JsonProperty("page")
	public Page getPage() {
		return _page;
	}

	private final Map<String, T> _content;
	private final Page _page;

	private class Page {

		public Page(
			Integer number, Integer size, Long totalElements,
			Integer totalPages) {

			_number = number;
			_size = size;
			_totalElements = totalElements;
			_totalPages = totalPages;
		}

		@JsonProperty("number")
		public Integer getNumber() {
			return _number;
		}

		@JsonProperty("size")
		public Integer getSize() {
			return _size;
		}

		@JsonProperty("totalElements")
		public Long getTotalElements() {
			return _totalElements;
		}

		@JsonProperty("totalPages")
		public Integer getTotalPages() {
			return _totalPages;
		}

		private final Integer _number;
		private final Integer _size;
		private final Long _totalElements;
		private final Integer _totalPages;

	}

}