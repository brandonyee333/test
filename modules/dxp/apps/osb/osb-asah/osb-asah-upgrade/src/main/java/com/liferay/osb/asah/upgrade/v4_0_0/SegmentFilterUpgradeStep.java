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
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void upgrade(String version) {
		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			Matcher matcher = _activityKeyPattern.matcher(filterString);

			while (matcher.find()) {
				String activityKey = matcher.group(1);

				String[] values = activityKey.split("#");

				String assetId = values[2];

				JSONObject assetJSONObject =
					_faroInfoElasticsearchInvoker.fetch("assets", assetId);

				if (assetJSONObject == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							String.format(
								"Unable to upgrade segment filter %s. " +
									"Segment ID: %s",
								filterString, segment.getId()));
					}
				}
				else {
					String dataSourceAssetPK = assetJSONObject.getString(
						"dataSourceAssetPK");

					filterString = filterString.replace(
						assetId, DigestUtils.sha256Hex(dataSourceAssetPK));
				}
			}

			if (!Objects.equals(filterString, segment.getFilter())) {
				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Upgrading segment filter from {%s} to {%s}",
							segment.getFilter(), filterString));
				}

				segment.setFilter(filterString);

				_segmentRepository.save(segment);
			}
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
		"activityKey eq ''([a-zA-z]+#[a-zA-z]+#[0-9]+)''");

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;
	private final SegmentRepository _segmentRepository;

}