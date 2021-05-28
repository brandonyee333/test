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

import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.model.Goal;
import com.liferay.osb.asah.common.util.StringUtil;

/**
 * @author Marcos Martins
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalDTO {

	public GoalDTO(Goal goal) {
		_goalMetric = StringUtil.get(goal.getGoalMetric(), null);
		_target = goal.getTarget();
	}

	@GraphQLProperty("metric")
	@JsonProperty("metric")
	public String getGoalMetric() {
		return _goalMetric;
	}

	public String getTarget() {
		return _target;
	}

	public void setGoalMetric(String goalMetric) {
		_goalMetric = goalMetric;
	}

	public void setTarget(String target) {
		_target = target;
	}

	private String _goalMetric;
	private String _target;

}