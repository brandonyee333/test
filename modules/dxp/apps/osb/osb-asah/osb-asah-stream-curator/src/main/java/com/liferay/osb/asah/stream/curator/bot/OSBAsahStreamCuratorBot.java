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

package com.liferay.osb.asah.stream.curator.bot;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component
@Profile("!test")
public class OSBAsahStreamCuratorBot {

	@PreDestroy
	public void destroy() {
		_scheduledExecutorFactoryBeans.forEach(
			ScheduledExecutorFactoryBean::destroy);
	}

	@PostConstruct
	public void init() {
		Stream<Nanite> stream = _nanites.stream();

		stream.collect(
			Collectors.groupingBy(
				nanite -> {
					Class<?> clazz = nanite.getClass();

					return clazz.getName();
				})
		).forEach(
			(name, nanites) -> _addScheduledExecutorFactoryBeans(
				name, nanites.get(0))
		);
	}

	private void _addScheduledExecutorFactoryBeans(String name, Nanite nanite) {
		ScheduledExecutorFactoryBean scheduledExecutorFactoryBean =
			new ScheduledExecutorFactoryBean();

		scheduledExecutorFactoryBean.
			setContinueScheduledExecutionAfterException(true);
		scheduledExecutorFactoryBean.setScheduledExecutorTasks(
			new ScheduledExecutorTask(
				nanite, DateUtil.SECOND * 5, nanite.getInterval(), false));
		scheduledExecutorFactoryBean.setThreadNamePrefix(
			String.format("osb-asah-stream-curator-bot[%s]", name));

		scheduledExecutorFactoryBean.initialize();

		_scheduledExecutorFactoryBeans.add(scheduledExecutorFactoryBean);
	}

	@Autowired
	private List<Nanite> _nanites;

	private final List<ScheduledExecutorFactoryBean>
		_scheduledExecutorFactoryBeans = new ArrayList<>();

}