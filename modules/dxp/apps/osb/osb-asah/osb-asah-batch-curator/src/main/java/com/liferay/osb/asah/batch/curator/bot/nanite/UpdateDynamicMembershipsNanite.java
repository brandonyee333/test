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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class UpdateDynamicMembershipsNanite extends BaseNanite {

	@Override
	public void logCompleted(
		String asahTaskId, JSONObject contextJSONObject, long duration) {
	}

	@Override
	public void logStart(JSONObject contextJSONObject) {
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Date modifiedDate = DateUtil.toUTCDate(
			contextJSONObject.getString("dateModified"));

		JSONObject individualSegmentJSONObject =
			contextJSONObject.optJSONObject("individualSegmentJSONObject");

		if (individualSegmentJSONObject != null) {
			_updateDynamicMembershipsForSegment(
				modifiedDate,
				_objectMapper.convertValue(
					individualSegmentJSONObject, Segment.class));

			return;
		}

		JSONObject individualJSONObject = contextJSONObject.optJSONObject(
			"individualJSONObject");

		if (individualJSONObject != null) {
			_updateDynamicMembershipsForIndividual(
				contextJSONObject.optString("filter", null),
				individualJSONObject.getLong("id"), modifiedDate);

			return;
		}

		String addFilter = contextJSONObject.optString("addFilter", null);
		String removeFilter = contextJSONObject.optString("removeFilter", null);

		if (Objects.equals(addFilter, removeFilter)) {
			_updateDynamicMembershipsForSegments(addFilter, modifiedDate);
		}
		else {
			_updateDynamicMembershipsForSegments(
				addFilter, modifiedDate, removeFilter);
		}
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(UpdateDynamicMembershipsNanite.class);
	}

	private void _updateDynamicMembershipsForIndividual(
		String filterString, Long individualId, Date modifiedDate) {

		Individual individual = _individualDog.fetchIndividual(individualId);

		if (individual == null) {
			return;
		}

		Boolean includeAnonymousUsers = Boolean.TRUE;

		if (CollectionUtils.isEmpty(individual.getFields()) ||
			Objects.isNull(
				FaroInfoIndividualUtil.getIndividualEmail(individual))) {

			includeAnonymousUsers = Boolean.FALSE;
		}

		JSONObject baseMembershipJSONObject = JSONUtil.put(
			"dateCreated", DateUtil.toUTCString(modifiedDate)
		).put(
			"dateModified", DateUtil.toUTCString(modifiedDate)
		).put(
			"individualId", individualId
		).put(
			"status", "ACTIVE"
		);

		try {
			int page = 0;

			while (true) {
				List<Segment> segments = _segmentDog.searchDynamicSegments(
					individual.getDataSourceAccountPKs(), filterString,
					includeAnonymousUsers, page++, individual.getSegmentIds(),
					50, Sort.asc("id"));

				if (segments.isEmpty()) {
					break;
				}

				for (Segment segment : segments) {
					_updateMembershipForIndividual(
						baseMembershipJSONObject, individual, segment,
						modifiedDate);
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _updateDynamicMembershipsForSegment(
			Date modifiedDate, Segment segment)
		throws Exception {

		Long segmentId = segment.getId();

		if (!_segmentDog.existsSegment(segmentId)) {
			return;
		}

		_individualDog.updateDynamicMemberships(modifiedDate, segment);

		_segmentDog.updateSegmentState(segmentId, "READY");
	}

	private void _updateDynamicMembershipsForSegments(
		String filterString, Date modifiedDate) {

		int page = 0;

		List<Segment> segments = _segmentDog.searchDynamicSegments(
			filterString, page, 500, null);

		while (!segments.isEmpty()) {
			for (Segment segment : segments) {
				try {
					_updateDynamicMembershipsForSegment(modifiedDate, segment);
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}

			segments = _segmentDog.searchDynamicSegments(
				filterString, ++page, 500, null);
		}
	}

	private void _updateDynamicMembershipsForSegments(
		String addFilterString, Date modifiedDate, String removeFilterString) {

		if (StringUtils.isNotEmpty(addFilterString)) {
			int page = 0;

			List<Segment> segments = _segmentDog.searchDynamicSegments(
				addFilterString, page, 500, null);

			while (!segments.isEmpty()) {
				for (Segment segment : segments) {
					try {
						_individualDog.updateDynamicMemberships(
							modifiedDate, segment);
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}

				segments = _segmentDog.searchDynamicSegments(
					addFilterString, ++page, 500, null);
			}
		}

		if (StringUtils.isNotEmpty(removeFilterString)) {
			int page = 0;

			List<Segment> segments = _segmentDog.searchDynamicSegments(
				removeFilterString, page, 500, null);

			while (!segments.isEmpty()) {
				for (Segment segment : segments) {
					try {
						_individualDog.updateDynamicRemoveMemberships(
							modifiedDate, segment);
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}

				segments = _segmentDog.searchDynamicSegments(
					removeFilterString, ++page, 500, null);
			}
		}
	}

	private void _updateMembershipForIndividual(
		JSONObject baseMembershipJSONObject, Individual individual,
		Segment segment, Date modifiedDate) {

		boolean newMember =
			_individualDog.existsByChannelIdAndFilterStringAndId(
				segment.getChannelId(), segment.getFilter(),
				individual.getId());

		Set<Long> segmentIds = individual.getSegmentIds();

		boolean oldMember = segmentIds.contains(segment.getId());

		if (newMember && !oldMember) {
			_membershipDog.addMembership(
				_objectMapper.convertValue(
					baseMembershipJSONObject.put(
						"individualSegmentId", segment.getId()),
					Membership.class));
		}
		else if (!newMember && oldMember) {
			_membershipDog.deactivateMembership(
				modifiedDate, individual.getId(), segment.getId());
		}
	}

	private static final Log _log = LogFactory.getLog(
		UpdateDynamicMembershipsNanite.class);

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}