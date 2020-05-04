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
 * @author Inácio Nery
 */
public enum TrendClassification {

	NEGATIVE, NEUTRAL, POSITIVE;

	public static TrendClassification classify(int trend, Order order) {
		if (trend == 0) {
			return NEUTRAL;
		}

		if (((trend > 0) && (order == Order.ASC)) ||
			((trend < 0) && (order == Order.DESC))) {

			return POSITIVE;
		}

		return NEGATIVE;
	}

	public enum Order {

		ASC, DESC

	}

}