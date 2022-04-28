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

package com.liferay.osb.asah.dataflow.emulator.model;

import java.util.Date;

/**
 * @author Marcos Martins
 */
public class AnalyticsDeleteMessage {

	public String getClassName() {
		return _className;
	}

	public Long getClassPK() {
		return _classPK;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(Long classPK) {
		_classPK = classPK;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	private String _className;
	private Long _classPK;
	private Date _modifiedDate;

}