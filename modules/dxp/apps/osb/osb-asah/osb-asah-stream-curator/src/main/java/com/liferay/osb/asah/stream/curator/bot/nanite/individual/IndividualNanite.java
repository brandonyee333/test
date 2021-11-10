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

package com.liferay.osb.asah.stream.curator.bot.nanite.individual;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.nio.charset.StandardCharsets;

import java.security.MessageDigest;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class IndividualNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "individuals";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		try {
			_semaphore.acquireUninterruptibly(4);
		}
		finally {
			_semaphore.release(4);
		}
	}

	@PreDestroy
	private void _destroy() {
		_reentrantLock.lock();

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	@PostConstruct
	private void _init() {
		String[] collections = JSONUtil.toStringArray(
			_elasticsearchIndexManager.getCollectionsJSONArray(
				WeDeployDataService.OSB_ASAH_CEREBRO_INFO));

		_collections = ArrayUtils.remove(
			collections, ArrayUtils.indexOf(collections, "user-sessions"));
	}

	private Set<Individual.ActivitiesCount> _mergeActivitiesCounts(
		Set<Individual.ActivitiesCount> activitiesCounts1,
		Set<Individual.ActivitiesCount> activitiesCounts2) {

		if (CollectionUtils.isEmpty(activitiesCounts1)) {
			activitiesCounts1 = Collections.emptySet();
		}

		if (CollectionUtils.isEmpty(activitiesCounts2)) {
			activitiesCounts2 = Collections.emptySet();
		}

		Map<Long, Long> mergedActivitiesCounts = new HashMap<>();

		Map<Long, Long> activitiesCounts1Map = _toMap(activitiesCounts1);

		Set<Map.Entry<Long, Long>> activitiesCounts1EntrySet =
			activitiesCounts1Map.entrySet();

		Map<Long, Long> activitiesCounts2Map = _toMap(activitiesCounts2);

		Set<Map.Entry<Long, Long>> activitiesCounts2EntrySet =
			activitiesCounts2Map.entrySet();

		List<Map.Entry<Long, Long>> mergedActivitiesCountEntrySet =
			Stream.concat(
				activitiesCounts1EntrySet.stream(),
				activitiesCounts2EntrySet.stream()
			).collect(
				Collectors.toList()
			);

		for (Map.Entry<Long, Long> mergedActivitiesCountEntry :
				mergedActivitiesCountEntrySet) {

			if (mergedActivitiesCounts.containsKey(
					mergedActivitiesCountEntry.getKey())) {

				Long activitiesCount1 = mergedActivitiesCounts.get(
					mergedActivitiesCountEntry.getKey());

				if (activitiesCount1 == null) {
					activitiesCount1 = 0L;
				}

				Long activitiesCount2 = mergedActivitiesCountEntry.getValue();

				if (activitiesCount2 == null) {
					activitiesCount2 = 0L;
				}

				mergedActivitiesCounts.put(
					mergedActivitiesCountEntry.getKey(),
					activitiesCount1 + activitiesCount2);

				continue;
			}

			mergedActivitiesCounts.put(
				mergedActivitiesCountEntry.getKey(),
				mergedActivitiesCountEntry.getValue());
		}

		Set<Individual.ActivitiesCount> individualActivitiesCounts =
			new HashSet<>();

		for (Map.Entry<Long, Long> entry : mergedActivitiesCounts.entrySet()) {
			individualActivitiesCounts.add(
				new Individual.ActivitiesCount(
					entry.getValue(), entry.getKey()));
		}

		return individualActivitiesCounts;
	}

	private void _mergeIndividual(
		Individual anonymousIndividual, Long dataSourceId,
		Individual knownIndividual, String userId) {

		_individualDog.addDataSourceIndividualPK(
			userId, dataSourceId, knownIndividual);

		Set<Individual.ActivitiesCount> knownIndividualActivitiesCounts =
			knownIndividual.getActivitiesCounts();

		Set<Individual.ActivitiesCount> anonymousIndividualActivitiesCounts =
			anonymousIndividual.getActivitiesCounts();

		knownIndividual.setActivitiesCounts(
			_mergeActivitiesCounts(
				anonymousIndividualActivitiesCounts,
				knownIndividualActivitiesCounts));

		Set<Long> channelIds = knownIndividual.getChannelIds();
		Set<IndividualChannel> individualChannels = new HashSet<>();

		for (Individual.ActivitiesCount activitiesCount :
				knownIndividual.getActivitiesCounts()) {

			channelIds.add(activitiesCount.getChannelId());

			IndividualChannel individualChannel = new IndividualChannel();

			individualChannel.setActivitiesCount(
				activitiesCount.getActivitiesCount());
			individualChannel.setChannelId(activitiesCount.getChannelId());
			individualChannel.setIndividualId(knownIndividual.getId());

			individualChannels.add(individualChannel);
		}

		knownIndividual.setIndividualChannels(individualChannels);

		Date date = new Date();

		knownIndividual.setLastEnrichmentDate(date);
		knownIndividual.setModifiedDate(date);

		_individualDog.updateIndividual(
			knownIndividual.getId(), knownIndividual, false);

		_individualDog.deleteIndividual(
			new Date(), anonymousIndividual.getId());
	}

	private void _run() throws Exception {
		while (true) {
			try {
				_reentrantLock.lock();

				long start = System.currentTimeMillis();

				List<String> messages = _messageSubscriber.pullMessages(50);

				if (messages.isEmpty()) {
					return;
				}

				Stream<String> stream = messages.stream();

				stream.map(
					JSONObject::new
				).collect(
					Collectors.groupingBy(
						jsonObject -> jsonObject.getString("projectId"))
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s dispatched %d messages in %d ms",
							clazz.getSimpleName(), messages.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(String projectId, List<JSONObject> messageJSONObjects) {
		_semaphore.acquireUninterruptibly();

		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), projectId);

		CompletableFuture.runAsync(
			() -> {
				try {
					long start = System.currentTimeMillis();

					reentrantLock.lock();

					ProjectIdThreadLocal.setProjectId(projectId);

					for (JSONObject messageJSONObject : messageJSONObjects) {
						try {
							String emailAddressHashed =
								messageJSONObject.getString(
									"emailAddressHashed");

							if ((emailAddressHashed == null) ||
								MessageDigest.isEqual(
									emailAddressHashed.getBytes(
										StandardCharsets.UTF_8),
									_BLANK_EMAIL_HASH.getBytes(
										StandardCharsets.UTF_8))) {

								continue;
							}

							if (!_suppressionDog.isSuppressed(
									null, emailAddressHashed)) {

								Long channelId = JSONUtil.optLong(
									null, messageJSONObject, "channelId");

								Individual individual = _updateIndividual(
									channelId,
									messageJSONObject.getLong("dataSourceId"),
									emailAddressHashed,
									messageJSONObject.getString("userId"));

								_updatePagesAndAssets(
									channelId,
									messageJSONObject.getLong("dataSourceId"),
									individual,
									messageJSONObject.getString("userId"));

								_updateUserSessions(
									messageJSONObject.getLong("dataSourceId"),
									individual.getId(),
									messageJSONObject.getString("userId"));
							}
						}
						catch (Exception exception) {
							_log.error(exception.getMessage(), exception);
						}
					}

					if (_log.isInfoEnabled()) {
						Class<?> clazz = getClass();

						_log.info(
							String.format(
								"%s processed %d messages in %d ms",
								clazz.getSimpleName(),
								messageJSONObjects.size(),
								System.currentTimeMillis() - start));
					}
				}
				finally {
					reentrantLock.unlock();

					_semaphore.release();
				}
			},
			_executorService);
	}

	private Map<Long, Long> _toMap(
		Set<Individual.ActivitiesCount> activitiesCounts) {

		Map<Long, Long> activitiesCountsMap = new HashMap<>();

		for (Individual.ActivitiesCount activitiesCount : activitiesCounts) {
			activitiesCountsMap.put(
				activitiesCount.getChannelId(),
				activitiesCount.getActivitiesCount());
		}

		return activitiesCountsMap;
	}

	private void _updateActivitiesAndActivityGroups(
			Individual individual, String userId)
		throws Exception {

		_faroInfoActivityDog.updateOwnerId(individual, userId);

		_activityGroupDog.updateActivityGroup(individual.getId(), userId);
	}

	private Individual _updateIndividual(
			Long channelId, Long dataSourceId, String emailAddressHashed,
			String userId)
		throws Exception {

		Individual individual1 = _individualDog.fetchIndividual(
			dataSourceId, userId);

		if ((individual1 != null) &&
			Objects.equals(
				individual1.getEmailAddressHashed(), emailAddressHashed)) {

			return individual1;
		}

		Individual individual2 =
			_individualDog.fetchIndividualByEmailAddressHashed(
				emailAddressHashed);

		if ((individual1 != null) && (individual2 != null)) {
			_mergeIndividual(individual1, dataSourceId, individual2, userId);
		}
		else if ((individual1 == null) && (individual2 == null)) {
			DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

			if (Objects.isNull(channelId)) {
				channelId = _dataSourceDog.getDefaultChannelId(dataSourceId);
			}

			return _individualDog.addIndividual(
				channelId, dataSource, emailAddressHashed, userId);
		}
		else {
			DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

			if (individual1 == null) {
				individual1 = individual2;
			}

			_individualDog.addDataSourceIndividualPK(
				userId, dataSource.getId(), individual1);

			individual2 = _updateIndividual(emailAddressHashed, individual1);
		}

		_updateActivitiesAndActivityGroups(individual2, userId);

		return individual2;
	}

	private Individual _updateIndividual(
		String emailAddressHashed, Individual individual) {

		individual.setEmailAddressHashed(emailAddressHashed);
		individual.setModifiedDate(new Date());

		return _individualDog.updateIndividual(individual);
	}

	private void _updatePagesAndAssets(
		Long channelId, Long dataSourceId, Individual individual,
		String userId) {

		StringBuilder sb = new StringBuilder();

		sb.append("ctx._source.individualId = params.individualId;");

		Map<String, Object> params = new HashMap<>();

		params.put("individualId", String.valueOf(individual.getId()));

		if (_fieldDog.isKnownIndividual(individual)) {
			sb.append("ctx._source.knownIndividual = true;");
		}

		List<String> segmentNames = _segmentDog.getSegmentNames(
			channelId, individual.getSegmentIds());

		if (!segmentNames.isEmpty()) {
			sb.append("ctx._source.segmentNames = params.segmentNames;");

			params.put("segmentNames", segmentNames);
		}

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("userId", userId)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				BoolQueryBuilderUtil.shouldNot(
					QueryBuilders.existsQuery("knownIndividual")
				).should(
					QueryBuilders.termQuery("knownIndividual", false)
				)
			),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				sb.toString(), params),
			_collections);
	}

	private void _updateUserSessions(
		Long dataSourceId, Long individualId, String userId) {

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.individualId = params.individualId;",
			Collections.singletonMap(
				"individualId", String.valueOf(individualId)));

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("userId", userId)
			).mustNot(
				QueryBuilders.termQuery("individualId", individualId)
			),
			true, script, "user-sessions");
	}

	private static final String _BLANK_EMAIL_HASH = DigestUtils.sha256Hex("");

	private static final Log _log = LogFactory.getLog(IndividualNanite.class);

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private String[] _collections;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.IDENTITY_MESSAGE)
	private MessageSubscriber _messageSubscriber;

	private final ReentrantLock _reentrantLock = new ReentrantLock();

	@Autowired
	private SegmentDog _segmentDog;

	private final Semaphore _semaphore = new Semaphore(4, true);

	@Autowired
	private SuppressionDog _suppressionDog;

}