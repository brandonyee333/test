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
import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoInterestDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

		Date dayDate = DateUtil.toUTCDate(dayDateString);

		long totalViews = _faroInfoActivityDog.getTotalPageViews(dayDateString);

		Map<String, List<KeywordInfo>> keywordInfosMap = _getKeywordInfosMap();

		Map<String, Long> totalKeywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap,
			_faroInfoActivityDog.getURLPageViewsMap(dayDateString, null, null));

		Set<Long> ownerIds = _faroInfoActivityDog.getOwnerIds(dayDateString);

		for (Long ownerId : ownerIds) {
			if (isInterrupted()) {
				return;
			}

			_process(
				dayDateString, keywordInfosMap, ownerId,
				totalKeywordsPageViewsMap, totalViews);
		}

		Long interestId = null;

		while (true) {
			List<Interest> interests = _interestDog.getInterests(
				interestId, "individual", DateUtil.addDays(dayDate, -1),
				_staleInterestsQuerySize);

			if (interests.isEmpty()) {
				break;
			}

			Interest lastInterest = interests.get(interests.size() - 1);

			interestId = lastInterest.getId();

			Iterator<Interest> iterator = interests.iterator();

			while (iterator.hasNext()) {
				if (isInterrupted()) {
					return;
				}

				Interest interest = iterator.next();

				if (!keywordInfosMap.containsKey(interest.getName()) ||
					ownerIds.contains(interest.getOwnerId())) {

					iterator.remove();

					continue;
				}

				double score = InterestScoreArm.DECAY * interest.getScore();

				if (score >= MINIMUM_INTEREST_SCORE_THRESHOLD) {
					interest.setId(null);
					interest.setRecordedDate(dayDate);
					interest.setScore(score);
				}
			}

			if (!interests.isEmpty()) {
				ListUtils.partition(
					interests, 1000
				).forEach(
					_interestDog::addInterests
				);
			}
		}

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
		String dayDateString, Long individualId, String keyword,
		List<KeywordInfo> keywordInfos, Map<String, Long> urlsPageViewsMap) {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

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

			if ((elasticsearchBulkRequestBuilder.getSize() % 1000) == 0) {
				elasticsearchBulkRequestBuilder.get();

				elasticsearchBulkRequestBuilder =
					faroInfoElasticsearchInvoker.
						createElasticsearchBulkRequestBuilder();
			}
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}
	}

	private void _deleteInterestScores(String dayDateString) throws Exception {
		Date dayDate = DateUtil.toUTCDate(dayDateString);

		_interestDog.deleteInterestsByExactRecordedDate("individual", dayDate);

		_interestDog.deleteInterests(
			"individual", DateUtil.addDays(dayDate, -2));

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

		Long currentAssetId = null;

		while (true) {
			List<Asset> assets = _assetDog.getAssets(
				currentAssetId, "Page", 500);

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

			Asset asset = assets.get(assets.size() - 1);

			currentAssetId = asset.getId();
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
		Long ownerId) {

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap,
			_faroInfoActivityDog.getURLPageViewsMap(
				dayDateString, ownerId, null));

		return keywordsPageViewsMap.entrySet();
	}

	private void _process(
		String dayDateString, Map<String, List<KeywordInfo>> keywordInfosMap,
		Long ownerId, Map<String, Long> totalKeywordsPageViewsMap,
		long totalViews) {

		Date dayDate = DateUtil.toUTCDate(dayDateString);

		long individualPageViews = _faroInfoActivityDog.getIndividualPageViews(
			dayDateString, ownerId);

		Map<String, Long> urlPageViewsMap =
			_faroInfoActivityDog.getURLPageViewsMap(
				dayDateString, ownerId, DateUtil.addDays(dayDateString, -30));

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap, urlPageViewsMap);

		Set<Map.Entry<String, Long>> urlsPageViewsEntries =
			_getURLsPageViewsEntrySet(dayDateString, keywordInfosMap, ownerId);

		Map<String, Interest> interestsMap = new ConcurrentHashMap<>(
			urlsPageViewsEntries.size());

		String projectId = ProjectIdThreadLocal.getProjectId();

		for (Map.Entry<String, Long> entry : urlsPageViewsEntries) {
			String keyword = entry.getKey();
			long pageViews = entry.getValue();

			_boundedExecutor.runAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					double score = 0;

					Interest interest = _interestDog.fetchInterest(
						keyword, ownerId, "individual",
						DateUtil.addDays(dayDate, -1));

					if (interest != null) {
						score = InterestScoreArm.DECAY * interest.getScore();
					}

					score = _interestScoreArm.computeScore(
						pageViews, individualPageViews, score,
						totalKeywordsPageViewsMap.get(keyword), totalViews);

					if (score < MINIMUM_INTEREST_SCORE_THRESHOLD) {
						return;
					}

					Interest newInterest = new Interest();

					newInterest.setName(keyword);
					newInterest.setOwnerId(ownerId);
					newInterest.setOwnerType("individual");
					newInterest.setRecordedDate(dayDate);
					newInterest.setScore(score);
					newInterest.setViews(keywordsPageViewsMap.get(keyword));

					interestsMap.put(keyword, newInterest);
				});
		}

		_boundedExecutor.awaitPendingTasks();

		for (String keyword : interestsMap.keySet()) {
			_addVisitedPages(
				dayDateString, ownerId, keyword, keywordInfosMap.get(keyword),
				urlPageViewsMap);
		}

		if (!interestsMap.isEmpty()) {
			ListUtils.partition(
				new ArrayList<>(interestsMap.values()), 1000
			).forEach(
				_interestDog::addInterests
			);
		}
	}

	private static final Log _log = LogFactory.getLog(
		IndividualInterestScoresNanite.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetDog _assetDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(50, 40);

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoInterestDog _faroInfoInterestDog;

	@Autowired
	private InterestDog _interestDog;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	private boolean _interrupted;
	private boolean _running;

	@Value("${osb.asah.batch.curator.stale.interests.query.size:10000}")
	private int _staleInterestsQuerySize;

}