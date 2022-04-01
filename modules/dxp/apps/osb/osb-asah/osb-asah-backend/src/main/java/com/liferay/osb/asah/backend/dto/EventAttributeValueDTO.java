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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.BQEventPropertyValue;

/**
 * @author Leslie Wong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("event-attribute-value")
public class EventAttributeValueDTO {

	public EventAttributeValueDTO() {
	}

	public EventAttributeValueDTO(BQEventPropertyValue bqEventPropertyValue) {
		_lastSeenDate = DateUtil.toUTCString(
			bqEventPropertyValue.getLastSeenDate());
		_value = bqEventPropertyValue.getValue();
	}

	@JsonProperty("lastSeenDate")
	public String getLastSeenDate() {
		return _lastSeenDate;
	}

	@JsonProperty("value")
	public String getValue() {
		return _value;
	}

	private String _lastSeenDate;
	private String _value;

}