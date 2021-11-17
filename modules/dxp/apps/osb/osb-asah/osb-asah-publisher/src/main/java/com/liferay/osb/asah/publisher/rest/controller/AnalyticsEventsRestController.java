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

package com.liferay.osb.asah.publisher.rest.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.AnalyticsEventUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eddie Olson
 */
@CrossOrigin
@RequestMapping("/")
@RestController(
	"com.liferay.osb.asah.publisher.rest.controller.AnalyticsEventsRestController"
)
public class AnalyticsEventsRestController {

	@PostMapping
	public ResponseEntity<?> post(
		@RequestBody @Valid AnalyticsEventsMessage analyticsEventsMessage,
		Errors errors, @RequestHeader HttpHeaders httpHeaders,
		HttpServletRequest httpServletRequest) {

		SimpleTimer simpleTimer = new SimpleTimer();

		try {
			if (!_isValidAnalyticsEventMessageId(
					analyticsEventsMessage.getId())) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Discarding duplicate message: " +
							analyticsEventsMessage.toJSON());
				}

				return new ResponseEntity<>(
					"Duplicate Message " + analyticsEventsMessage.getId(),
					HttpStatus.OK);
			}

			if (analyticsEventsMessage.getId() != null) {
				_analyticsEventMessageIds.put(
					analyticsEventsMessage.getId(),
					analyticsEventsMessage.getId());
			}

			String clientIP = httpHeaders.getFirst("X-Forwarded-For");

			if (clientIP == null) {
				clientIP = httpServletRequest.getRemoteAddr();
			}
			else {
				String[] parts = clientIP.split(",");

				clientIP = parts[0];
			}

			analyticsEventsMessage.setClientIP(clientIP);

			List<AnalyticsEventsMessage.Event> events =
				analyticsEventsMessage.getEvents();

			int eventsSize = events.size();

			List<FieldError> fieldErrors = errors.getFieldErrors();

			if (errors.hasErrors()) {
				if (StringUtils.isEmpty(
						analyticsEventsMessage.getDataSourceId())) {

					_invalidate(analyticsEventsMessage);

					return new ResponseEntity<>(
						errors.getAllErrors(), HttpStatus.BAD_REQUEST);
				}

				Stream<FieldError> stream = fieldErrors.stream();

				Optional<FieldError> fieldErrorOptional = stream.filter(
					fieldError -> StringUtils.startsWith(
						fieldError.getField(), "context")
				).findAny();

				if (fieldErrorOptional.isPresent()) {
					return new ResponseEntity<>(
						errors.getFieldError(), HttpStatus.BAD_REQUEST);
				}

				for (int index : _getInvalidEventIndices(fieldErrors)) {
					events.remove(index);
				}

				analyticsEventsMessage.setEvents(events);
			}

			analyticsEventsMessage.setProjectId(
				ProjectIdThreadLocal.getProjectId());

			if (!events.isEmpty()) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Pushing analytics events message to the queue " +
							analyticsEventsMessage.toJSON());
				}

				_messageBus.sendMessage(
					Channel.ANALYTICS_EVENTS_MESSAGE,
					analyticsEventsMessage.toJSON());
			}
			else {
				_invalidate(analyticsEventsMessage);
			}

			if (eventsSize != events.size()) {
				return new ResponseEntity<>(fieldErrors, HttpStatus.OK);
			}

			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
		}
		finally {
			_eventRequestsHistogram.observe(simpleTimer.elapsedSeconds());
		}
	}

	private Set<Integer> _getInvalidEventIndices(List<FieldError> fieldErrors) {
		Set<Integer> indices = new TreeSet<>(Collections.reverseOrder());

		for (FieldError fieldError : fieldErrors) {
			Matcher matcher = _pattern.matcher(fieldError.getField());

			if (matcher.matches()) {
				indices.add(Integer.parseInt(matcher.group(1)));
			}
		}

		return indices;
	}

	private void _invalidate(AnalyticsEventsMessage analyticsEventsMessage) {
		if (analyticsEventsMessage.getId() != null) {
			_analyticsEventMessageIds.invalidate(
				analyticsEventsMessage.getId());
		}
	}

	private boolean _isValidAnalyticsEventMessageId(String id) {
		if (id == null) {
			return true;
		}

		if (_analyticsEventMessageIds.getIfPresent(id) != null) {
			return false;
		}

		return true;
	}

	private static final long _CACHE_EXPIRATION_TIME = 60;

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventsRestController.class);

	private static final Cache<String, String> _analyticsEventMessageIds =
		Caffeine.newBuilder(
		).expireAfterAccess(
			_CACHE_EXPIRATION_TIME, TimeUnit.MINUTES
		).maximumSize(
			100000
		).build();
	private static final Histogram _eventRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_event_request_seconds",
			"The number of seconds taken to process the event requests");
	private static final Pattern _pattern = Pattern.compile(
		"^events\\[(\\d+)].*");

	@Autowired

	@Autowired
	private MessageBus _messageBus;

}