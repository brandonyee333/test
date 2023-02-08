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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class SegmentFilterUpgradeStep implements UpgradeStep {

	public SegmentFilterUpgradeStep(SegmentRepository segmentRepository) {
		_segmentRepository = segmentRepository;
	}

	@Override
	public void upgrade(String version) throws Exception {
		String query = ResourceUtil.readResourceToString(
			new ClassPathResource("query-asset-event-4.0.0.sql"));

		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			Matcher matcher = _activityKeyPattern.matcher(segment.getFilter());

			while (matcher.find()) {
				String activityKey = filterString.substring(
					matcher.start(), matcher.end());

				String[] values = activityKey.split("#");

				String id = _namedParameterJdbcTemplate.queryForObject(
					query,
					new HashMap<String, Object>() {
						{
							put("applicationId", values[0]);
							put("dataSourceId", Long.parseLong(values[2]));
							put("eventId", values[1]);
						}
					},
					String.class);

				if (id == null) {
					throw new IllegalStateException(
						String.format("Asset %s does not exist", activityKey));
				}

				filterString = filterString.replace(activityKey, id);

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Activity key %s replaced with %s", activityKey,
							id));
				}
			}

			segment.setFilter(filterString);

			_segmentRepository.save(segment);
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private static final Log _log = LogFactory.getLog(
		SegmentFilterUpgradeStep.class);

	private static final Pattern _activityKeyPattern = Pattern.compile(
		"([a-zA-z])+#([a-zA-z])+#([0-9])+");

	@Autowired
	private DataSource _dataSource;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;
	private final SegmentRepository _segmentRepository;

}