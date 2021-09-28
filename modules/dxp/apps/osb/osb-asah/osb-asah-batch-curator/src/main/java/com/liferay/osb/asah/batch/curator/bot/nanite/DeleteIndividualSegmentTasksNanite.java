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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONArrayIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

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
		Long individualSegmentId = contextJSONObject.getLong(
			"individualSegmentId");

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerId", individualSegmentId)
		).filter(
			QueryBuilders.termQuery("ownerType", "individual-segment")
		);

		_interestDog.deleteInterest(individualSegmentId, "individual-segment");

		faroInfoElasticsearchInvoker.delete("visited-pages", boolQueryBuilder);

		membershipChangeDog.deleteMembershipChanges(individualSegmentId);

		membershipDog.deleteMembership(individualSegmentId);

		JSONArrayIterator.of(
			"individuals", faroInfoElasticsearchInvoker,
			jsonObject -> {
				_individualDog.removeSegmentId(
					_objectMapper.convertValue(jsonObject, Individual.class),
					individualSegmentId);

				return null;
			}
		).setMonitoringConsumers(
			this::monitorProcessedCount, this::monitorQueueSize
		).setQueryBuilder(
			QueryBuilders.termQuery("individualSegmentIds", individualSegmentId)
		).iterate();
	}

	@Autowired
	@Override
	protected Log getLog() {
		return LogFactory.getLog(DeleteIndividualSegmentTasksNanite.class);
	}

	@Autowired
	protected MembershipChangeDog membershipChangeDog;

	@Autowired
	protected MembershipDog membershipDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private ObjectMapper _objectMapper;

}