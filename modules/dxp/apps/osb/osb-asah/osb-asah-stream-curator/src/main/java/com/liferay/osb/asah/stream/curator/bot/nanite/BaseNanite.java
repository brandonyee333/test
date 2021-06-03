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
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;
import com.liferay.osb.asah.stream.curator.model.Model;
import com.liferay.osb.asah.stream.curator.model.ModelMapper;
import com.liferay.osb.asah.stream.curator.model.page.BasePageModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public abstract class BaseNanite<T extends Model> implements Nanite {

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception exception) {
			Log log = getLog();

			log.error(exception, exception);
		}
	}

	protected String digest(Object... objects) {
		return NaniteUtil.digest(objects);
	}

	protected void doRun() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<AnalyticsEvent> analyticsEvents = pullAnalyticsEvents();

			if (analyticsEvents.isEmpty()) {
				break;
			}

			Stream<AnalyticsEvent> stream = analyticsEvents.stream();

			stream.collect(
				Collectors.groupingBy(AnalyticsEvent::getProjectId)
			).forEach(
				this::_run
			);

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

	protected abstract MessageSubscriber getMessageSubscriber();

	protected Collection<T> getModels(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> analyticsEventsStream = analyticsEvents.stream();

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

			Stream<T> modelsStream = models.stream();

			map = modelsStream.collect(
				Collectors.toMap(
					T::getPrimaryKey, Function.identity(),
					getBinaryOperator()));
		}

		return map.values();
	}

	protected abstract Supplier<T> getModelSupplier();

	protected abstract Function<T, String> getPrimaryKeyGeneratorFunction();

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

	protected List<AnalyticsEvent> pullAnalyticsEvents() throws Exception {
		MessageSubscriber messageSubscriber = getMessageSubscriber();

		return messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);
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

		Map<String, String> context = analyticsEvent.getContext();

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

		_setModelIndividualProperties(analyticsEvent, model);
		_setModelLocation(analyticsEvent, model);
		_setModelTechnology(analyticsEvent, model);
		_setModelTitle(analyticsEvent, model);
		_setModelURL(analyticsEvent, model);

		return model;
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

	private void _mergeURLs(T oldModel, T newModel) {
		if (oldModel.isAsset() && newModel.isAsset()) {
			BaseAssetModel oldAssetModel = (BaseAssetModel)oldModel;
			BaseAssetModel newAssetModel = (BaseAssetModel)newModel;

			oldAssetModel.addCanonicalURLs(newAssetModel.getCanonicalUrls());
			oldAssetModel.addURLs(newAssetModel.getURLs());
		}
	}

	private void _run(String projectId, List<AnalyticsEvent> analyticsEvents) {
		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			saveModels(getModels(analyticsEvents));
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
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

	private void _setModelIndividualProperties(
		AnalyticsEvent analyticsEvent, T model) {

		if (_faroInfoElasticsearchInvoker.exists(
				"individuals", analyticsEvent.getIndividualId())) {

			model.setIndividualId(analyticsEvent.getIndividualId());
			model.setKnownIndividual(analyticsEvent.isKnownIndividual());
			model.setSegmentNames(analyticsEvent.getSegmentNames());

			return;
		}

		JSONObject individualJSONObject =
			_faroInfoIndividualDog.getIndividualJSONObject(
				Long.valueOf(analyticsEvent.getDataSourceId()),
				analyticsEvent.getUserId());

		if (individualJSONObject != null) {
			model.setIndividualId(individualJSONObject.getString("id"));
			model.setKnownIndividual(
				FaroInfoIndividualUtil.isKnownIndividual(individualJSONObject));
			model.setSegmentNames(
				new HashSet<>(
					_segmentDog.getSegmentNames(
						Long.valueOf(analyticsEvent.getChannelId()),
						JSONUtil.toLongSet(
							individualJSONObject.getJSONArray(
								"individualSegmentIds")))));
		}
	}

	private void _setModelLocation(AnalyticsEvent analyticsEvent, T model) {
		Map<String, String> context = analyticsEvent.getContext();

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

	private void _setModelTechnology(AnalyticsEvent analyticsEvent, T model) {
		Map<String, String> context = analyticsEvent.getContext();

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
		String canonicalUrl = MapUtil.getString(
			analyticsEvent.getContext(), "canonicalUrl");
		String url = MapUtil.getString(analyticsEvent.getContext(), "url");

		if (model.isAsset()) {
			BaseAssetModel baseAssetModel = (BaseAssetModel)model;

			baseAssetModel.addCanonicalURL(canonicalUrl);
			baseAssetModel.addURL(url);
		}
		else {
			BasePageModel basePageModel = (BasePageModel)model;

			basePageModel.setCanonicalUrl(canonicalUrl);
			basePageModel.setURL(url);
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private SegmentDog _segmentDog;

}