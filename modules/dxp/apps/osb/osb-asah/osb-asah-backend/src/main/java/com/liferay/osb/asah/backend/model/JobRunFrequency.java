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

/**
 * @author Marcellus Tavares
 */
public enum JobTrainingFrequency {

	EVERY_7_DAYS("0 0 0 */7 * ?"), EVERY_14_DAYS("0 0 0 */14 * ?"),
	EVERY_30_DAYS("0 0 0 */30 * ?"), MANUAL(null);

	public String getCronExpression() {
		return _cronExpression;
	}

	private JobTrainingFrequency(String cronExpression) {
		_cronExpression = cronExpression;
	}

	private final String _cronExpression;

}