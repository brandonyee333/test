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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignCanonicalUrlArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignSessionActivitiesArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignSessionContextArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.AssignTempUrlsArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.SyncPageActivitiesEventContextArm;
import com.liferay.osb.asah.common.function.UnsafeRunnable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UpgradeNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		_execute(
			_syncPageActivitiesEventContextArm.getClass(),
			_syncPageActivitiesEventContextArm::execute);

		_execute(_assignTempUrlsArm.getClass(), _assignTempUrlsArm::execute);

		_execute(
			_assignCanonicalUrlArm.getClass(), _assignCanonicalUrlArm::execute);

		_execute(
			_assignSessionActivitiesArm.getClass(),
			_assignSessionActivitiesArm::execute);

		_execute(
			_assignSessionContextArm.getClass(),
			_assignSessionContextArm::execute);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _execute(
			Class<?> clazz, UnsafeRunnable<Exception> clazzUnsafeRunnable)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Start " + clazz.getName());
		}

		clazzUnsafeRunnable.run();

		if (_log.isInfoEnabled()) {
			_log.info("End " + clazz.getName());
		}
	}

	private static final Log _log = LogFactory.getLog(UpgradeNanite.class);

	@Autowired
	private AssignCanonicalUrlArm _assignCanonicalUrlArm;

	@Autowired
	private AssignSessionActivitiesArm _assignSessionActivitiesArm;

	@Autowired
	private AssignSessionContextArm _assignSessionContextArm;

	@Autowired
	private AssignTempUrlsArm _assignTempUrlsArm;

	@Autowired
	private SyncPageActivitiesEventContextArm
		_syncPageActivitiesEventContextArm;

}