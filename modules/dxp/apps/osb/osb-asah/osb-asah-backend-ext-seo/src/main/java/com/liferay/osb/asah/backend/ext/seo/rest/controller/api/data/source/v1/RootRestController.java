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

import com.liferay.osb.asah.backend.ext.seo.model.SearchKeyword;
import com.liferay.osb.asah.backend.ext.seo.model.TrafficSource;
import com.liferay.osb.asah.common.spring.http.Http;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author David Arques
 */
@RequestMapping("/api/seo/1.0")
@RestController(
	"com.liferay.osb.asah.backend.ext.seo.rest.controller.api.data.source.v1.RootRestController"
)
public class RootRestController {

	@Cacheable("getTrafficSources")
	@GetMapping("/traffic-sources")
	public List<TrafficSource> getTrafficSources(@RequestParam String url) {
		if (StringUtils.isEmpty(url)) {
			throw new IllegalArgumentException("URL is null");
		}

		List<TrafficSource> trafficSources = new ArrayList<>();

		Optional<Integer> organicSearchKeywordsTotalTrafficAmountOptional =
			_getSearchKeywordsTotalTrafficAmount("url_organic", url);
		Optional<Integer> paidSearchKeywordsTotalTrafficAmountOptional =
			_getSearchKeywordsTotalTrafficAmount("url_adwords", url);

		int totalTrafficAmount = Math.addExact(
			organicSearchKeywordsTotalTrafficAmountOptional.orElse(0),
			paidSearchKeywordsTotalTrafficAmountOptional.orElse(0));

		organicSearchKeywordsTotalTrafficAmountOptional.ifPresent(
			organicSearchKeywordsTotalTrafficAmount -> trafficSources.add(
				new TrafficSource(
					"organic", organicSearchKeywordsTotalTrafficAmount,
					_calculatePercentage(
						organicSearchKeywordsTotalTrafficAmount,
						totalTrafficAmount))));
		paidSearchKeywordsTotalTrafficAmountOptional.ifPresent(
			paidSearchKeywordsTotalTrafficAmount -> trafficSources.add(
				new TrafficSource(
					"paid", paidSearchKeywordsTotalTrafficAmount,
					_calculatePercentage(
						paidSearchKeywordsTotalTrafficAmount,
						totalTrafficAmount))));

		return trafficSources;
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

	private List<SearchKeyword> _getSearchKeywords(String body) {
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

	private Optional<Integer> _getSearchKeywordsTotalTrafficAmount(
		String type, String url) {

		try {
			UriComponentsBuilder uriComponentsBuilder =
				UriComponentsBuilder.fromHttpUrl("https://api.semrush.com/");

			// TODO Use country codes to set database (LPS-111042)

			uriComponentsBuilder.queryParam("database", "us");
			uriComponentsBuilder.queryParam("display_filter", "+|Tg|Gt|0");
			uriComponentsBuilder.queryParam("display_limit", 100);
			uriComponentsBuilder.queryParam("display_sort", "tg_desc");
			uriComponentsBuilder.queryParam("export_columns", "Ph,Po,Nq,Tg");
			uriComponentsBuilder.queryParam(
				"key", _environment.getProperty("SEMRUSH_API_KEY"));
			uriComponentsBuilder.queryParam("type", type);
			uriComponentsBuilder.queryParam("url", url);

			UriComponents uriComponents = uriComponentsBuilder.build();

			ResponseEntity<String> responseEntity =
				_http.exchangeResponseEntity(
					uriComponents.toUriString(), null, HttpMethod.GET, null);

			if (!Objects.equals(
					responseEntity.getStatusCode(), HttpStatus.OK)) {

				if (_logger.isDebugEnabled()) {
					_logger.debug(
						String.format(
							"Unexpected response status code %s",
							responseEntity.getStatusCode()));
				}

				return Optional.empty();
			}

			List<SearchKeyword> searchKeywords = _getSearchKeywords(
				responseEntity.getBody());

			Stream<SearchKeyword> stream = searchKeywords.stream();

			return Optional.of(
				stream.mapToInt(
					SearchKeyword::getTraffic
				).sum());
		}
		catch (Exception exception) {
			_logger.error(
				String.format(
					"Unable to get search keywords total traffic amount of " +
						"type %s",
					type),
				exception);

			return Optional.empty();
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		RootRestController.class);

	@Autowired
	private Environment _environment;

	@Autowired
	private Http _http;

}