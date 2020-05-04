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

import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
public enum ExperimentStatus {

	COMPLETED(true), DRAFT(true), FINISHED_NO_WINNER(false),
	FINISHED_WINNER(false), PAUSED(true), RUNNING(false), SCHEDULED(true),
	TERMINATED(true);

	public static boolean isValidTransition(
		ExperimentStatus fromExperimentStatus,
		ExperimentStatus toExperimentStatus) {

		if ((fromExperimentStatus == null) || (toExperimentStatus == null)) {
			return false;
		}

		if (Objects.equals(fromExperimentStatus, toExperimentStatus)) {
			return true;
		}

		Set<ExperimentStatus> possibleStatusesSet =
			_validTransitions.getOrDefault(
				fromExperimentStatus, Collections.emptySet());

		return possibleStatusesSet.contains(toExperimentStatus);
	}

	public boolean isDeleteAllowed() {
		return _deleteAllowed;
	}

	private ExperimentStatus(boolean deleteAllowed) {
		_deleteAllowed = deleteAllowed;
	}

	private static final Map<ExperimentStatus, Set<ExperimentStatus>>
		_validTransitions =
			new HashMap<ExperimentStatus, Set<ExperimentStatus>>() {
				{
					put(COMPLETED, Collections.emptySet());
					put(DRAFT, SetUtil.of(RUNNING, SCHEDULED));
					put(FINISHED_NO_WINNER, SetUtil.of(COMPLETED));
					put(FINISHED_WINNER, SetUtil.of(COMPLETED));
					put(PAUSED, SetUtil.of(RUNNING));
					put(
						RUNNING,
						SetUtil.of(
							FINISHED_NO_WINNER, FINISHED_WINNER, PAUSED,
							TERMINATED));
					put(SCHEDULED, SetUtil.of(RUNNING));
					put(TERMINATED, Collections.emptySet());
				}
			};

	private final boolean _deleteAllowed;

}