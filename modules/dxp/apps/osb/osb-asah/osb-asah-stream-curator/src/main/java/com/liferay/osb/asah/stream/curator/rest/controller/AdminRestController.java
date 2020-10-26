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

package com.liferay.osb.asah.stream.curator.rest.controller;

import com.liferay.osb.asah.stream.curator.bot.nanite.session.SessionFinalizerNanite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author André Miranda
 */
@Profile("dev")
@RequestMapping("/admin")
@RestController
public class AdminRestController {

	@PostMapping("/sessions/close")
	public void closeSessions() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Starting to close all sessions");
		}

		_sessionFinalizerNanite.run(true);

		if (_log.isInfoEnabled()) {
			_log.info("All sessions were closed");
		}
	}

	private static final Log _log = LogFactory.getLog(
		AdminRestController.class);

	@Autowired
	private SessionFinalizerNanite _sessionFinalizerNanite;

}