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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchJobRunRepositoryImpl
	extends BaseElasticsearchRepository<JobRun, Long>
	implements JobRunRepository {

	@Override
	public long countByJobId(Long jobId) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryBuilders.termQuery("job.id", jobId.toString()));
	}

	@Override
	public void deleteByJobId(Long id) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(), QueryBuilders.termQuery("job.id", id));
	}

	@Override
	public boolean existsByJobIdAndJobRunStatus(
		Long jobId, JobRunStatus jobRunStatus) {

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", jobId.toString())
			).filter(
				QueryBuilders.termsQuery("status", jobRunStatus.toString())
			));
	}

	@Override
	public List<JobRun> findByJobId(Long jobId, Pageable pageable) {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.termQuery(
								"job.id", jobId.toString()));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<JobRun> findByJobIdAndCreateLocalDateTimeBetween(
		Long jobId, LocalDateTime startCreateLocalDateTime,
		LocalDateTime endCreateLocalDateTime) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						RangeQueryBuilder rangeQueryBuilder =
							QueryBuilders.rangeQuery("createdDate");

						rangeQueryBuilder.gte(
							DateUtil.toUTCString(startCreateLocalDateTime));
						rangeQueryBuilder.lt(
							DateUtil.toUTCString(endCreateLocalDateTime));

						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								rangeQueryBuilder
							).filter(
								QueryBuilders.termQuery(
									"job.id", jobId.toString())
							));
					})));
	}

	@Override
	public List<JobRun> findByJobRunStatusAndJobTypeAndStep(
		JobRunStatus jobRunStatus, String jobType, String step) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("job.type", jobType)
				).filter(
					QueryBuilders.termQuery("status", jobRunStatus.toString())
				).filter(
					QueryBuilders.termsQuery("step", step)
				)));
	}

	@Override
	public Optional<JobRun> findFirstByJobIdAndJobRunStatusOrderByIdDesc(
		Long jobId, JobRunStatus jobRunStatus) {

		return findFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("job.id", jobId.toString())
					).filter(
						QueryBuilders.termQuery(
							"status", jobRunStatus.toString())
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});
	}

	@Override
	public Optional<JobRun> findFirstByJobIdAndTriggerOrderByIdDesc(
		Long id, String trigger) {

		return findFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("job.id", id)
					).filter(
						QueryBuilders.termQuery("trigger", trigger)
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});
	}

	@Override
	public Optional<JobRun> findFirstByJobIdOrderByIdDesc(Long jobId) {
		return findFirst(
			searchSourceBuilder -> {
				searchSourceBuilder.query(
					QueryBuilders.termQuery("job.id", jobId.toString()));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.DESC));
			});
	}

	@Override
	protected String getCollectionName() {
		return "job-runs";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}