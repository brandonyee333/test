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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.model.AnalyticsEventsMessage;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			if (!_analyticsEventsMessageCache.add(
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

			if (errors.hasErrors()) {
				if (StringUtils.isEmpty(
						analyticsEventsMessage.getDataSourceId())) {

					_analyticsEventsMessageCache.remove(
						analyticsEventsMessage.getId());

					return new ResponseEntity<>(
						errors.getAllErrors(), HttpStatus.BAD_REQUEST);
				}

				for (int index :
						_getInvalidEventIndices(errors.getFieldErrors())) {

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
				_analyticsEventsMessageCache.remove(
					analyticsEventsMessage.getId());
			}

			if (eventsSize != events.size()) {
				return new ResponseEntity<>(
					errors.getFieldErrors(), HttpStatus.OK);
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

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventsRestController.class);

	private static final Histogram _eventRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_event_request_seconds",
			"The number of seconds taken to process the event requests");
	private static final Pattern _pattern = Pattern.compile(
		"^events\\[(\\d+)].*");

	@Autowired
	private AnalyticsEventsMessageCache _analyticsEventsMessageCache;

	@Autowired
	private MessageBus _messageBus;

}