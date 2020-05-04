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
import com.liferay.osb.asah.publisher.cache.AnalyticsEventsMessageCache;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
			if (_analyticsEventsMessageCache.has(
					analyticsEventsMessage.getId())) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Discarding duplicate message: " +
							analyticsEventsMessage.toString());
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

					return new ResponseEntity<>(
						errors.getAllErrors(), HttpStatus.BAD_REQUEST);
				}

				ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));

				// Tolerate 1 hour of discrepancy between sever and client dates

				now = now.plusHours(1);

				Iterator<AnalyticsEventsMessage.Event> iterator =
					events.iterator();

				while (iterator.hasNext()) {
					AnalyticsEventsMessage.Event event = iterator.next();

					if (!_isValid(event)) {
						iterator.remove();

						continue;
					}

					Date eventDate = event.getEventDate();

					Instant eventDateInstant = eventDate.toInstant();

					if (eventDateInstant.isAfter(now.toInstant())) {
						iterator.remove();
					}
				}

				analyticsEventsMessage.setEvents(events);
			}

			if (!events.isEmpty()) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Pushing analytics events message to the queue " +
							analyticsEventsMessage.toJSON());
				}

				_messageBus.sendMessage(
					Channel.ANALYTICS_EVENTS_MESSAGE,
					analyticsEventsMessage.toJSON());

				_analyticsEventsMessageCache.add(
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

	private boolean _isValid(AnalyticsEventsMessage.Event event) {
		if (StringUtils.isBlank(event.getApplicationId()) ||
			(event.getEventDate() == null) ||
			StringUtils.isBlank(event.getEventId())) {

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactory.getLog(
		AnalyticsEventsRestController.class);

	private static final Histogram _eventRequestsHistogram =
		PrometheusUtil.histogram(
			"publisher_event_request_seconds",
			"The number of seconds taken to process the event requests");

	@Autowired
	private AnalyticsEventsMessageCache _analyticsEventsMessageCache;

	@Autowired
	private MessageBus _messageBus;

}