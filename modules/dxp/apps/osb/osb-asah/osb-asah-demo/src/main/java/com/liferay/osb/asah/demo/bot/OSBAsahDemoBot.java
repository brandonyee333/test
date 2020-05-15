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

package com.liferay.osb.asah.demo.bot;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.demo.bot.nanite.Nanite;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@EnableScheduling
public class OSBAsahDemoBot {

	@Scheduled(
		fixedDelay = DateUtil.SECOND * 20, initialDelay = DateUtil.SECOND * 5
	)
	public void run() {
		Stream<Nanite> stream = _dataGenerators.parallelStream();

		stream.forEach(Nanite::run);
	}

	@Autowired
	private List<Nanite> _dataGenerators;

}