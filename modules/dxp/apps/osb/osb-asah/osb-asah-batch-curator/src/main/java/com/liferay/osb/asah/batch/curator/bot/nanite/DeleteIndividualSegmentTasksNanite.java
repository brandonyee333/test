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

import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class DeleteIndividualSegmentTasksNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONArray jsonArray = contextJSONObject.getJSONArray(
			"individualSegmentIds");

		List<Long> individualSegmentIds = ListUtil.map(
			jsonArray.toList(),
			individualSegmentId -> Long.valueOf((int)individualSegmentId));

		bqMembershipChangeDog.deleteBQMembershipChanges(individualSegmentIds);

		bqMembershipDog.deleteBQMemberships(individualSegmentIds);
	}

	@Autowired
	@Override
	protected Log getLog() {
		return LogFactory.getLog(DeleteIndividualSegmentTasksNanite.class);
	}

	@Autowired
	protected BQMembershipChangeDog bqMembershipChangeDog;

	@Autowired
	protected BQMembershipDog bqMembershipDog;

}