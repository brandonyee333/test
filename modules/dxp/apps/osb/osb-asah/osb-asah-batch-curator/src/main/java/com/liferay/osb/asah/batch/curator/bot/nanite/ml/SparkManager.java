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

package com.liferay.osb.asah.batch.curator.bot.nanite.ml;

import java.util.List;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 * @author Marcellus Tavares
 */
public interface SparkManager {

	public JobState getJobState(String sparkJobId);

	public String submitJob(
		List<String> arguments, String configuration, List<String> jars,
		String name, Map<String, String> properties);

}