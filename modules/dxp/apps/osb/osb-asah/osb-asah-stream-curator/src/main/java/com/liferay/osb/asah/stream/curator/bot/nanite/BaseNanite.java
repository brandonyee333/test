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
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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
			_run();
		}
		catch (Exception exception) {
			Log log = getLog();

			log.error(exception, exception);
		}

		try {
			_semaphore.acquireUninterruptibly(4);
		}
		finally {
			_semaphore.release(4);
		}
	}

	protected String digest(Object... objects) {
		return NaniteUtil.digest(objects);
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
				T::getPrimaryKey, Function.identity(), getBinaryOperator(),
				LinkedHashMap::new)
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
					T::getPrimaryKey, Function.identity(), getBinaryOperator(),
					LinkedHashMap::new));
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
			Log log = getLog();

			log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
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

	private void _run() throws Exception {
		while (true) {
			try {
				_reentrantLock.lock();

				long start = System.currentTimeMillis();

				List<AnalyticsEvent> analyticsEvents = pullAnalyticsEvents();

				if (analyticsEvents.isEmpty()) {
					break;
				}

				Stream<AnalyticsEvent> stream = analyticsEvents.stream();

				stream.collect(
					Collectors.groupingBy(
						analyticsEvent -> Tuples.of(
							analyticsEvent.getProjectId(),
							analyticsEvent.getUserId()))
				).forEach(
					this::_run
				);

				Log log = getLog();

				if (log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					log.info(
						String.format(
							"%s dispatched %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(
		Tuple2<String, String> tuple2, List<AnalyticsEvent> analyticsEvents) {

		_semaphore.acquireUninterruptibly();

		CompletableFuture.runAsync(
			() -> {
				try {
					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(tuple2.getT1());

					saveModels(getModels(analyticsEvents));

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
				catch (Exception exception) {
					Log log = getLog();

					log.error(exception.getMessage(), exception);
				}
				finally {
					_semaphore.release();
				}
			},
			_executorService);
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

		if ((analyticsEvent.getIndividualId() != null) &&
			_individualDog.existsById(
				Long.valueOf(analyticsEvent.getIndividualId()))) {

			model.setIndividualId(analyticsEvent.getIndividualId());
			model.setKnownIndividual(analyticsEvent.isKnownIndividual());
			model.setSegmentNames(analyticsEvent.getSegmentNames());

			return;
		}

		if (analyticsEvent.getDataSourceId() != null) {
			Individual individual = _individualDog.fetchIndividual(
				Long.valueOf(analyticsEvent.getDataSourceId()),
				analyticsEvent.getUserId());

			if (individual != null) {
				model.setIndividualId(String.valueOf(individual.getId()));
				model.setKnownIndividual(
					FaroInfoIndividualUtil.isKnownIndividual(individual));
				model.setSegmentNames(
					new HashSet<>(
						_segmentDog.getSegmentNames(
							Long.valueOf(analyticsEvent.getChannelId()),
							individual.getSegmentIds())));
			}
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

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(2);

	@Autowired
	private IndividualDog _individualDog;

	private final ReentrantLock _reentrantLock = new ReentrantLock();

	@Autowired
	private SegmentDog _segmentDog;

	private final Semaphore _semaphore = new Semaphore(4, true);

}