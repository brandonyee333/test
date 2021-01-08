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

package com.liferay.osb.asah.stream.curator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;
import com.liferay.osb.asah.stream.curator.model.Model;
import com.liferay.osb.asah.stream.curator.model.ModelMapper;
import com.liferay.osb.asah.stream.curator.model.page.BasePageModel;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 * @author Brian Wing Shun Chan
 */
public abstract class BaseNanite<T extends Model> implements Nanite {

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@PostConstruct
	public void init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			Log log = getLog();

			log.error(e, e);
		}
	}

	protected String digest(Object... objects) {
		return NaniteUtil.digest(objects);
	}

	protected void doRun() throws Exception {
		JSONObject osbAsahMarkerJSONObject = _getOSBAsahMarkerJSONObject();

		while (true) {
			long start = System.currentTimeMillis();

			String lastSuccessfulAnalyticsEventId =
				osbAsahMarkerJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", "0");

			BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					lastSuccessfulAnalyticsEventId
				)
			).filter(
				getQueryBuilder()
			);

			_monitorNaniteQueueSize(boolQueryBuilder);

			String analyticsEventsJSON = _cerebroRawElasticsearchInvoker.get(
				"analytics-events",
				searchSourceBuilder -> {
					searchSourceBuilder.query(boolQueryBuilder);
					searchSourceBuilder.size(50);
					searchSourceBuilder.sort("id");
				});

			List<AnalyticsEvent> analyticsEvents =
				AnalyticsEvent.toAnalyticsEvents(analyticsEventsJSON);

			if (analyticsEvents.isEmpty()) {
				break;
			}

			_cerebroInfoElasticsearchInvoker.save(
				getCollectionName(),
				ModelMapper.toJSONArray(getModels(analyticsEvents)));

			AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
				analyticsEvents.size() - 1);

			osbAsahMarkerJSONObject.put(
				"lastSuccessfulAnalyticsEventId", lastAnalyticsEvent.getId());

			_cerebroInfoElasticsearchInvoker.update(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);

			_monitorNaniteProcessedEventsCount(analyticsEvents.size());

			Log log = getLog();

			if (log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				log.info(
					String.format(
						"%s processed %d events in %d ms",
						clazz.getSimpleName(), analyticsEvents.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	protected Function<T, String> getAssetPrimaryKeyGeneratorFunction() {
		return model -> {
			if (model instanceof BaseAssetModel) {
				BaseAssetModel baseAssetModel = (BaseAssetModel)model;

				return digest(
					baseAssetModel.getAssetId(), baseAssetModel.getChannelId());
			}

			return null;
		};
	}

	protected abstract BinaryOperator<T> getBinaryOperator();

	protected abstract Predicate<T> getFilterPredicate();

	protected abstract Log getLog();

	protected Collection<T> getModels(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> analyticsEventsStream =
			analyticsEvents.parallelStream();

		Map<String, T> map = analyticsEventsStream.distinct(
		).map(
			_getMapperFunction()
		).filter(
			getFilterPredicate()
		).collect(
			Collectors.toMap(
				T::getPrimaryKey, Function.identity(), getBinaryOperator())
		);

		Supplier<T> supplier = getModelSupplier();

		T model = supplier.get();

		List<T> models = ModelMapper.toModels(
			_cerebroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termsQuery(
					"primaryKey", new ArrayList<>(map.keySet()))),
			model.getClass());

		if (!models.isEmpty()) {
			models.addAll(map.values());

			Stream<T> modelsStream = models.parallelStream();

			map = modelsStream.collect(
				Collectors.toMap(
					T::getPrimaryKey, Function.identity(),
					getBinaryOperator()));
		}

		return map.values();
	}

	protected abstract Supplier<T> getModelSupplier();

	protected abstract Function<T, String> getPrimaryKeyGeneratorFunction();

	protected abstract QueryBuilder getQueryBuilder();

	protected T mergeModels(T oldModel, T newModel) {
		if (oldModel.getSegmentNames() != null) {
			oldModel.addSegmentNames(newModel.getSegmentNames());
		}
		else {
			oldModel.setSegmentNames(newModel.getSegmentNames());
		}

		oldModel.setIndividualId(newModel.getIndividualId());
		oldModel.setKnownIndividual(newModel.isKnownIndividual());

		Date newLastEventDate = newModel.getLastEventDate();
		Date oldLastEventDate = oldModel.getLastEventDate();

		if ((newLastEventDate != null) &&
			((oldLastEventDate == null) ||
			 newLastEventDate.after(oldLastEventDate))) {

			oldModel.setLastEventDate(newLastEventDate);
		}

		oldModel.setModifiedDate(newModel.getModifiedDate());

		if (StringUtils.isNotEmpty(newModel.getTitle()) &&
			(StringUtils.isEmpty(oldModel.getTitle()) ||
			 Objects.equals(newModel.getContentLanguageId(), "en-US"))) {

			oldModel.setContentLanguageId(newModel.getContentLanguageId());
			oldModel.setTitle(newModel.getTitle());
		}

		_mergeURLs(oldModel, newModel);

		return oldModel;
	}

	protected void saveModels(Collection<T> models) {
		_cerebroInfoElasticsearchInvoker.save(
			getCollectionName(), ModelMapper.toJSONArray(models));
	}

	protected abstract void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, T model);

	private T _createModel(AnalyticsEvent analyticsEvent) {
		Supplier<T> supplier = getModelSupplier();

		T model = supplier.get();

		Map<String, Object> context = analyticsEvent.getContext();

		model.setChannelId(analyticsEvent.getChannelId());

		model.setContentLanguageId(
			MapUtil.getString(context, "contentLanguageId"));

		model.setDataSourceId(analyticsEvent.getDataSourceId());
		model.setEventDate(analyticsEvent.getNormalizedEventDate());
		model.setExperienceId(MapUtil.getString(context, "experienceId", ""));
		model.setExperimentId(MapUtil.getString(context, "experimentId", ""));
		model.setLastEventDate(analyticsEvent.getEventDate());
		model.setModifiedDate(new Date());
		model.setUserId(analyticsEvent.getUserId());
		model.setVariantId(MapUtil.getString(context, "variantId", ""));

		Optional<JSONObject> individualJSONObjectOptional =
			_fetchIndividualJSONObjectOptional(analyticsEvent);

		if (individualJSONObjectOptional.isPresent()) {
			_setModelIndividualId(individualJSONObjectOptional.get(), model);
			_setModelKnownIndividual(individualJSONObjectOptional.get(), model);
		}

		_setModelLocation(analyticsEvent, model);

		if (individualJSONObjectOptional.isPresent()) {
			_setModelSegmentNames(individualJSONObjectOptional.get(), model);
		}

		_setModelTechnology(analyticsEvent, model);
		_setModelTitle(analyticsEvent, model);
		_setModelURL(analyticsEvent, model);

		return model;
	}

	private Optional<JSONObject> _fetchIndividualJSONObjectOptional(
		AnalyticsEvent analyticsEvent) {

		if ((analyticsEvent.getDataSourceId() == null) ||
			(analyticsEvent.getUserId() == null)) {

			return Optional.ofNullable(null);
		}

		try {
			JSONObject individualJSONObject =
				_faroInfoElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.nestedQuery(
						"dataSourceIndividualPKs",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"dataSourceIndividualPKs.dataSourceId",
								analyticsEvent.getDataSourceId())
						).filter(
							QueryBuilders.termsQuery(
								"dataSourceIndividualPKs.individualPKs",
								analyticsEvent.getUserId())
						),
						ScoreMode.None));

			return Optional.ofNullable(individualJSONObject);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<String> _getIndividualSegmentIds(
		JSONObject individualJSONObject) {

		JSONArray individualSegmentIds = individualJSONObject.getJSONArray(
			"individualSegmentIds");

		return JSONUtil.toStringList(individualSegmentIds);
	}

	private Function<AnalyticsEvent, T> _getMapperFunction() {
		return analyticsEvent -> {
			T t = _createModel(analyticsEvent);

			setModelCustomProperties(analyticsEvent, t);

			// Primary key must be called after all other model properties are
			// set

			_setAssetPrimaryKey(t);
			_setModelPrimaryKey(t);

			return t;
		};
	}

	private JSONObject _getOSBAsahMarkerJSONObject() {
		Class<?> clazz = getClass();

		JSONObject osbAsahMarkerJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", clazz.getSimpleName());

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = new JSONObject();

			osbAsahMarkerJSONObject.put("id", clazz.getSimpleName());

			_cerebroInfoElasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject;
	}

	private void _mergeURLs(T oldModel, T newModel) {
		if (oldModel.isAsset() && newModel.isAsset()) {
			BaseAssetModel oldAssetModel = (BaseAssetModel)oldModel;
			BaseAssetModel newAssetModel = (BaseAssetModel)newModel;

			oldAssetModel.addURLs(newAssetModel.getURLs());
		}
	}

	private void _monitorNaniteProcessedEventsCount(int total) {
		Class<?> clazz = getClass();

		Counter.Child child = _analyticsEventsCount.labels(
			clazz.getSimpleName());

		child.inc(total);
	}

	private void _monitorNaniteQueueSize(QueryBuilder queryBuilder) {
		Class<?> clazz = getClass();

		Gauge.Child child = _analyticsEventsQueueSize.labels(
			clazz.getSimpleName());

		child.set(
			_cerebroRawElasticsearchInvoker.count(
				"analytics-events", queryBuilder));
	}

	private void _setAssetPrimaryKey(T model) {
		if (model instanceof BaseAssetModel) {
			BaseAssetModel baseAssetModel = (BaseAssetModel)model;

			Function<T, String> assetPrimaryKeyGeneratorFunction =
				getAssetPrimaryKeyGeneratorFunction();

			String assetPrimaryKey = assetPrimaryKeyGeneratorFunction.apply(
				model);

			baseAssetModel.setAssetPrimaryKey(assetPrimaryKey);
		}
	}

	private void _setModelIndividualId(
		JSONObject individualJSONObject, T model) {

		model.setIndividualId(individualJSONObject.getString("id"));
	}

	private void _setModelKnownIndividual(
		JSONObject individualJSONObject, T model) {

		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if (demographicsJSONObject == null) {
			model.setKnownIndividual(false);
		}
		else {
			model.setKnownIndividual(demographicsJSONObject.has("email"));
		}
	}

	private void _setModelLocation(AnalyticsEvent analyticsEvent, T model) {
		Map<String, Object> context = analyticsEvent.getContext();

		model.setCity(MapUtil.getString(context, "city"));
		model.setCountry(MapUtil.getString(context, "country"));
		model.setRegion(MapUtil.getString(context, "region"));
	}

	private void _setModelPrimaryKey(T model) {
		Function<T, String> primaryKeyGeneratorFunction =
			getPrimaryKeyGeneratorFunction();

		String primaryKey = primaryKeyGeneratorFunction.apply(model);

		model.setPrimaryKey(primaryKey);
	}

	private void _setModelSegmentNames(
		JSONObject individualJSONObject, T model) {

		List<String> individualSegmentIds = _getIndividualSegmentIds(
			individualJSONObject);

		if (individualSegmentIds.isEmpty()) {
			return;
		}

		try {
			BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("id", individualSegmentIds)
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			);

			model.setSegmentNames(
				JSONUtil.toStringSet(
					_faroInfoElasticsearchInvoker.get(
						"individual-segments", boolQueryBuilder),
					"name"));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void _setModelTechnology(AnalyticsEvent analyticsEvent, T model) {
		Map<String, Object> context = analyticsEvent.getContext();

		model.setBrowserName(MapUtil.getString(context, "browserName"));
		model.setDeviceType(MapUtil.getString(context, "deviceType"));
		model.setPlatformName(MapUtil.getString(context, "platformName"));
	}

	private void _setModelTitle(AnalyticsEvent analyticsEvent, T model) {
		if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			return;
		}

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (eventProperties == null) {
			return;
		}

		String title = eventProperties.get("title");

		if (StringUtils.isNotEmpty(title)) {
			model.setTitle(title);
		}
	}

	private void _setModelURL(AnalyticsEvent analyticsEvent, T model) {
		String url = MapUtil.getString(analyticsEvent.getContext(), "url");

		if (model.isAsset()) {
			BaseAssetModel baseAssetModel = (BaseAssetModel)model;

			baseAssetModel.addURL(url);
		}
		else {
			BasePageModel basePageModel = (BasePageModel)model;

			basePageModel.setURL(url);
		}
	}

	private static final Counter _analyticsEventsCount = PrometheusUtil.counter(
		"curator_analytics_events_count",
		"The number of analytics events processed", "nanite");
	private static final Gauge _analyticsEventsQueueSize = PrometheusUtil.gauge(
		"curator_analytics_events_queue_size",
		"The number of analytics events queued to be curated", "nanite");

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}