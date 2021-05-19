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

package com.liferay.osb.asah.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;

/**
 * @author Marcellus Tavares
 */
public class Goal {

	public Goal() {
	}

	public Goal(GoalMetric goalMetric, String target) {
		_goalMetric = goalMetric;
		_target = target;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Goal)) {
			return false;
		}

		Goal goal = (Goal)obj;

		if (Objects.equals(_goalMetric, goal._goalMetric) &&
			Objects.equals(_target, goal._target)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("metric")
	@JsonProperty("metric")
	@NotNull
	public GoalMetric getGoalMetric() {
		return _goalMetric;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTarget() {
		return _target;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_goalMetric, _target);
	}

	public void setGoalMetric(GoalMetric goalMetric) {
		_goalMetric = goalMetric;
	}

	public void setTarget(String target) {
		_target = target;
	}

	@Transient
	private GoalMetric _goalMetric;

	@Transient
	private String _target;

}