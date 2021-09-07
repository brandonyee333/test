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

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.InterestScoreArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.KeywordInfo;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoInterestDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualInterestScoresNanite extends BaseScoresNanite {

	public static final double MINIMUM_INTEREST_SCORE_THRESHOLD = 0.01;

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(String dayDateString) throws Exception {
		_deleteInterestScores(dayDateString);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		long totalViews = _faroInfoActivityDog.getTotalPageViews(dayDateString);

		Map<String, List<KeywordInfo>> keywordInfosMap = _getKeywordInfosMap();

		Map<String, Long> totalKeywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap,
			_faroInfoActivityDog.getURLPageViewsMap(dayDateString, null, null));

		Set<String> ownerIds = _faroInfoActivityDog.getOwnerIds(dayDateString);

		for (String ownerId : ownerIds) {
			if (isInterrupted()) {
				return;
			}

			_process(
				dayDateString, elasticsearchBulkRequestBuilder, keywordInfosMap,
				ownerId, totalKeywordsPageViewsMap, totalViews);
		}

		JSONArrayIterator.of(
			"interests", faroInfoElasticsearchInvoker,
			interestJSONObject -> {
				try {
					if (ownerIds.contains(
							interestJSONObject.getString("ownerId"))) {

						return elasticsearchBulkRequestBuilder;
					}

					double score =
						InterestScoreArm.DECAY *
							interestJSONObject.getDouble("score");

					if (score >= MINIMUM_INTEREST_SCORE_THRESHOLD) {
						interestJSONObject.remove("id");

						elasticsearchBulkRequestBuilder.add(
							"interests",
							interestJSONObject.put(
								"dateRecorded", dayDateString
							).put(
								"score", score
							));
					}
				}
				catch (Exception exception) {
					return exception;
				}

				return elasticsearchBulkRequestBuilder;
			}
		).setBatchSize(
			10000
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dateRecorded", DateUtil.addDays(dayDateString, -1))
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			)
		).iterate();

		faroInfoElasticsearchInvoker.refresh();

		_faroInfoInterestDog.clearCache();
	}

	@Override
	protected void afterOSBAsahMarkerUpdated(
		String lastSuccessfulDayDateString) {

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter", "contains(filter, 'interests.filter(')"
			).put(
				"dateModified", lastSuccessfulDayDateString
			).put(
				"removeFilter", "contains(filter, 'interests.filter(')"
			));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected boolean isInterrupted() {
		return _interrupted;
	}

	@Override
	protected boolean isRunning() {
		return _running;
	}

	@Override
	protected void process(
		Map<String, Object> context, String dayDateString,
		JSONObject individualJSONObject) {
	}

	@Override
	protected void setInterrupted(boolean interrupted) {
		_interrupted = interrupted;
	}

	@Override
	protected void setRunning(boolean running) {
		_running = running;
	}

	private void _addVisitedPages(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		String dayDateString, String individualId, String keyword,
		List<KeywordInfo> keywordInfos, Map<String, Long> urlsPageViewsMap) {

		for (KeywordInfo keywordInfo : keywordInfos) {
			Long pageViews = urlsPageViewsMap.get(
				keywordInfo.getDataSourceAssetPK());

			if (pageViews == null) {
				continue;
			}

			elasticsearchBulkRequestBuilder.add(
				"visited-pages",
				JSONUtil.put(
					"canonicalUrl", keywordInfo.getCanonicalUrl()
				).put(
					"day", dayDateString
				).put(
					"interestName", keyword
				).put(
					"ownerId", individualId
				).put(
					"ownerType", "individual"
				).put(
					"title", keywordInfo.getName()
				).put(
					"uniqueVisitsCount", pageViews
				).put(
					"url", keywordInfo.getDataSourceAssetPK()
				));
		}
	}

	private void _deleteInterestScores(String dayDateString) throws Exception {
		faroInfoElasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).lte(
					DateUtil.addDays(dayDateString, -2)
				)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			),
			false, "interests");
		faroInfoElasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"day"
				).lt(
					DateUtil.addDays(dayDateString, -30)
				)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			),
			false, "visited-pages");
	}

	private Map<String, List<KeywordInfo>> _getKeywordInfosMap() {
		Map<String, List<KeywordInfo>> keywordInfosMap = new HashMap<>();

		int page = 0;

		while (true) {
			List<Asset> assets = _assetDog.getAssets(
				"Page", page++, 500, Sort.asc("id"));

			if (assets.isEmpty()) {
				break;
			}

			assets.forEach(
				asset -> {
					String dataSourceAssetPK = asset.getDataSourceAssetPK();

					String canonicalUrl = Optional.ofNullable(
						asset.getCanonicalURL()
					).orElse(
						dataSourceAssetPK
					);

					String title = asset.getTitle();

					Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

					assetKeywords.forEach(
						assetKeyword -> {
							List<KeywordInfo> keywordInfos =
								keywordInfosMap.computeIfAbsent(
									assetKeyword.getKeyword(),
									key -> new ArrayList<>());

							keywordInfos.add(
								new KeywordInfo(
									canonicalUrl, dataSourceAssetPK, title));
						});
				});
		}

		return keywordInfosMap;
	}

	private Map<String, Long> _getKeywordsPageViewsMap(
		Map<String, List<KeywordInfo>> keywordInfosMap,
		Map<String, Long> urlPageViewsMap) {

		Map<String, Long> keywordsPageViewsMap = new HashMap<>();

		for (Map.Entry<String, List<KeywordInfo>> entry :
				keywordInfosMap.entrySet()) {

			String keyword = entry.getKey();

			for (KeywordInfo keywordInfo : entry.getValue()) {
				keywordsPageViewsMap.merge(
					keyword,
					urlPageViewsMap.getOrDefault(
						keywordInfo.getDataSourceAssetPK(), 0L),
					Long::sum);
			}
		}

		return keywordsPageViewsMap;
	}

	private Set<Map.Entry<String, Long>> _getURLsPageViewsEntrySet(
		String dayDateString, Map<String, List<KeywordInfo>> keywordInfosMap,
		String ownerId) {

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap,
			_faroInfoActivityDog.getURLPageViewsMap(
				dayDateString, ownerId, null));

		return keywordsPageViewsMap.entrySet();
	}

	private void _process(
			String dayDateString,
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
			Map<String, List<KeywordInfo>> keywordInfosMap, String ownerId,
			Map<String, Long> totalKeywordsPageViewsMap, long totalViews)
		throws Exception {

		long individualPageViews = _faroInfoActivityDog.getIndividualPageViews(
			dayDateString, ownerId);

		Map<String, Long> urlPageViewsMap =
			_faroInfoActivityDog.getURLPageViewsMap(
				dayDateString, ownerId, DateUtil.addDays(dayDateString, -30));

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap, urlPageViewsMap);

		for (Map.Entry<String, Long> entry :
				_getURLsPageViewsEntrySet(
					dayDateString, keywordInfosMap, ownerId)) {

			String keyword = entry.getKey();
			long pageViews = entry.getValue();

			double score = 0;

			JSONObject interestJSONObject = faroInfoElasticsearchInvoker.fetch(
				"interests",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dateRecorded", DateUtil.addDays(dayDateString, -1))
				).filter(
					QueryBuilders.termQuery("name", keyword)
				).filter(
					QueryBuilders.termQuery("ownerId", ownerId)
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				));

			if (interestJSONObject != null) {
				score =
					InterestScoreArm.DECAY *
						interestJSONObject.getDouble("score");
			}

			score = _interestScoreArm.computeScore(
				pageViews, individualPageViews, score,
				totalKeywordsPageViewsMap.get(keyword), totalViews);

			if (score < MINIMUM_INTEREST_SCORE_THRESHOLD) {
				continue;
			}

			elasticsearchBulkRequestBuilder.add(
				"interests",
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"name", keyword
				).put(
					"ownerId", ownerId
				).put(
					"ownerType", "individual"
				).put(
					"score", score
				).put(
					"views", keywordsPageViewsMap.get(keyword)
				));

			_addVisitedPages(
				elasticsearchBulkRequestBuilder, dayDateString, ownerId,
				keyword, keywordInfosMap.get(keyword), urlPageViewsMap);
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}
	}

	private static final Log _log = LogFactory.getLog(
		IndividualInterestScoresNanite.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoInterestDog _faroInfoInterestDog;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	private boolean _interrupted;
	private boolean _running;

}