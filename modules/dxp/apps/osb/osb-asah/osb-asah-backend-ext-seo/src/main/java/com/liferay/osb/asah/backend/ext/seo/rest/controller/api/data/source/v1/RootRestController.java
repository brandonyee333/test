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

package com.liferay.osb.asah.backend.ext.seo.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.backend.ext.seo.model.CountrySearchKeywords;
import com.liferay.osb.asah.backend.ext.seo.model.SearchKeyword;
import com.liferay.osb.asah.backend.ext.seo.model.TrafficSource;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.http.Http;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.charset.StandardCharsets;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author David Arques
 */
@EnableScheduling
@RequestMapping("/api/seo/1.0")
@RestController(
	"com.liferay.osb.asah.backend.ext.seo.rest.controller.api.data.source.v1.RootRestController"
)
public class RootRestController {

	@CacheEvict(allEntries = true, value = "getTrafficSources")
	@DeleteMapping("/cache")
	@Scheduled(cron = "0 0 2 ? * MON")
	public void clearCache() {
		if (_log.isDebugEnabled()) {
			_log.debug("Clearing cache getTrafficSources");
		}
	}

	@Cacheable("getTrafficSources")
	@GetMapping("/traffic-sources")
	public List<TrafficSource> getTrafficSources(@RequestParam String url) {
		if (StringUtils.isEmpty(url)) {
			throw new IllegalArgumentException("URL is null");
		}

		Set<String> databases = _getDatabases(url);

		List<CountrySearchKeywords> organicCountrySearchKeywordsList =
			_getCountrySearchKeywordsList(
				databases, _urlOrganicDisplayLimit, "url_organic", url);

		int organicSearchKeywordsTotalTraffic = _getSearchKeywordsTotalTraffic(
			organicCountrySearchKeywordsList);

		List<CountrySearchKeywords> paidCountrySearchKeywordsList =
			_getCountrySearchKeywordsList(
				databases, _urlAdwordsDisplayLimit, "url_adwords", url);

		int paidSearchKeywordsTotalTraffic = _getSearchKeywordsTotalTraffic(
			paidCountrySearchKeywordsList);

		return Arrays.asList(
			new TrafficSource(
				organicCountrySearchKeywordsList, "organic",
				organicSearchKeywordsTotalTraffic,
				_calculatePercentage(
					organicSearchKeywordsTotalTraffic,
					organicSearchKeywordsTotalTraffic +
						paidSearchKeywordsTotalTraffic)),
			new TrafficSource(
				paidCountrySearchKeywordsList, "paid",
				paidSearchKeywordsTotalTraffic,
				_calculatePercentage(
					paidSearchKeywordsTotalTraffic,
					organicSearchKeywordsTotalTraffic +
						paidSearchKeywordsTotalTraffic)));
	}

	private double _calculatePercentage(int value, int total) {
		double percentage = 0;

		if (total != 0) {
			percentage = (double)value * 100 / total;
		}

		BigDecimal bigDecimal = BigDecimal.valueOf(percentage);

		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);

		return bigDecimal.doubleValue();
	}

	private List<CountrySearchKeywords> _getCountrySearchKeywordsList(
		Set<String> databases, int displayLimit, String type, String url) {

		Stream<String> stream = databases.stream();

		return stream.map(
			database -> new CountrySearchKeywords(
				database, _getSearchKeywords(database, displayLimit, type, url))
		).collect(
			Collectors.toList()
		);
	}

	private Set<String> _getDatabases(String url) {
		Set<String> databases = new LinkedHashSet<>();

		JSONArray jsonArray = new JSONArray(
			_http.exchange(
				ServiceConstants.URL_BACKEND_INTERNAL,
				"/api/1.0/pages/geolocations?canonicalUrl=" + url,
				HttpMethod.GET, null));

		Iterator<Object> iterator = jsonArray.iterator();

		while (iterator.hasNext() && (databases.size() < _databasesLimit)) {
			JSONObject jsonObject = (JSONObject)iterator.next();

			String database = _databases.get(jsonObject.getString("valueKey"));

			if (database != null) {
				databases.add(database);
			}
		}

		return databases;
	}

	private List<SearchKeyword> _getSearchKeywords(
		String database, int displayLimit, String type, String url) {

		UriComponentsBuilder uriComponentsBuilder =
			UriComponentsBuilder.fromHttpUrl("https://api.semrush.com/");

		uriComponentsBuilder.queryParam("database", database);
		uriComponentsBuilder.queryParam("display_filter", "+|Tg|Gt|0");
		uriComponentsBuilder.queryParam("display_limit", displayLimit);
		uriComponentsBuilder.queryParam("display_sort", "tg_desc");
		uriComponentsBuilder.queryParam("export_columns", "Ph,Po,Nq,Tg");
		uriComponentsBuilder.queryParam(
			"key", _environment.getProperty("SEMRUSH_API_KEY", _semrushAPIKey));
		uriComponentsBuilder.queryParam("type", type);
		uriComponentsBuilder.queryParam("url", url);

		UriComponents uriComponents = uriComponentsBuilder.build();

		ResponseEntity<String> responseEntity = _http.exchangeResponseEntity(
			uriComponents.toUriString(), null, HttpMethod.GET, null);

		if (!Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK)) {
			throw new HttpClientErrorException(responseEntity.getStatusCode());
		}

		return _toSearchKeywords(responseEntity.getBody());
	}

	private int _getSearchKeywordsTotalTraffic(
		List<CountrySearchKeywords> countrySearchKeywordsList) {

		Stream<CountrySearchKeywords> stream =
			countrySearchKeywordsList.stream();

		return stream.map(
			CountrySearchKeywords::getSearchKeywords
		).flatMap(
			Collection::stream
		).mapToInt(
			SearchKeyword::getTraffic
		).sum();
	}

	private List<SearchKeyword> _toSearchKeywords(String body) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();

		CsvFormat csvFormat = csvParserSettings.getFormat();

		csvFormat.setDelimiter(';');

		csvParserSettings.setHeaderExtractionEnabled(true);

		BeanListProcessor<SearchKeyword> beanListProcessor =
			new BeanListProcessor<>(SearchKeyword.class);

		csvParserSettings.setProcessor(beanListProcessor);

		CsvParser csvParser = new CsvParser(csvParserSettings);

		csvParser.parse(
			new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

		return beanListProcessor.getBeans();
	}

	private static final Log _log = LogFactory.getLog(RootRestController.class);

	private static final Map<String, String> _databases = Stream.of(
		new AbstractMap.SimpleImmutableEntry<>("Australia", "au"),
		new AbstractMap.SimpleImmutableEntry<>("Brazil", "br"),
		new AbstractMap.SimpleImmutableEntry<>("Canada", "ca"),
		new AbstractMap.SimpleImmutableEntry<>("Finland", "fi"),
		new AbstractMap.SimpleImmutableEntry<>("France", "fr"),
		new AbstractMap.SimpleImmutableEntry<>("Germany", "de"),
		new AbstractMap.SimpleImmutableEntry<>("Hungary", "hu"),
		new AbstractMap.SimpleImmutableEntry<>("India", "in"),
		new AbstractMap.SimpleImmutableEntry<>("Italy", "it"),
		new AbstractMap.SimpleImmutableEntry<>("Japan", "jp"),
		new AbstractMap.SimpleImmutableEntry<>("Netherlands", "nl"),
		new AbstractMap.SimpleImmutableEntry<>("Russia", "ru"),
		new AbstractMap.SimpleImmutableEntry<>("Saudi Arabia", "sa"),
		new AbstractMap.SimpleImmutableEntry<>("Spain", "es"),
		new AbstractMap.SimpleImmutableEntry<>("Sweden", "se"),
		new AbstractMap.SimpleImmutableEntry<>("United Kingdom", "uk"),
		new AbstractMap.SimpleImmutableEntry<>("United States", "us"),
		new AbstractMap.SimpleImmutableEntry<>("Unknown", "us")
	).collect(
		Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
	);

	@Value("${osb.asah.seo.semrush.databases.limit:5}")
	private int _databasesLimit;

	@Autowired
	private Environment _environment;

	@Autowired
	private Http _http;

	@Value("${osb.asah.seo.semrush.api.key}")
	private String _semrushAPIKey;

	@Value("${osb.asah.seo.semrush.url.adwords.display.limit:10}")
	private int _urlAdwordsDisplayLimit;

	@Value("${osb.asah.seo.semrush.url.organic.display.limit:25}")
	private int _urlOrganicDisplayLimit;

}