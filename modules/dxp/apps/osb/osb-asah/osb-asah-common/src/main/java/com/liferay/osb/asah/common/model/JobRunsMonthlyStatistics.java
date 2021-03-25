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

/**
 * @author Marcellus Tavares
 */
public class JobRunsMonthlyStatistics {

	public long getAvailableJobRuns() {
		return Math.max(
			0,
			_maxJobRuns -
				(_completedJobRuns + _publishedJobRuns + _runningJobRuns));
	}

	public long getCompletedJobRuns() {
		return _completedJobRuns;
	}

	public long getFailedJobRuns() {
		return _failedJobRuns;
	}

	public long getPublishedJobRuns() {
		return _publishedJobRuns;
	}

	public long getRunningJobRuns() {
		return _runningJobRuns;
	}

	public long getScheduledJobRuns() {
		return _scheduledJobRuns;
	}

	public void setCompletedJobRuns(long completedJobRuns) {
		_completedJobRuns = completedJobRuns;
	}

	public void setFailedJobRuns(long failedJobRuns) {
		_failedJobRuns = failedJobRuns;
	}

	public void setMaxJobRuns(long maxJobRuns) {
		_maxJobRuns = maxJobRuns;
	}

	public void setPublishedJobRuns(long publishedJobRuns) {
		_publishedJobRuns = publishedJobRuns;
	}

	public void setRunningJobRuns(long runningJobRuns) {
		_runningJobRuns = runningJobRuns;
	}

	public void setScheduledJobRuns(long scheduledJobRuns) {
		_scheduledJobRuns = scheduledJobRuns;
	}

	private long _completedJobRuns;
	private long _failedJobRuns;
	private long _maxJobRuns;
	private long _publishedJobRuns;
	private long _runningJobRuns;
	private long _scheduledJobRuns;

}