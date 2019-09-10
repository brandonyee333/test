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

package com.liferay.lcs.client.internal.command.queue;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Igor Beslic
 */
@Component(
	property = {
		"osgi.command.scope=lcs", "osgi.command.function=commandStatus"
	},
	service = CommandQueue.class
)
public class CommandQueue {

	public void add(List<Message> messages) {
		for (Message message : messages) {
			_add(message);
		}
	}

	public void add(Message message) {
		_add(message);
	}

	public String commandStatus() {
		StringBundler sb = new StringBundler(
			(_commanMessageHistory.size() * 2) + 1);

		sb.append(
			String.format(
				"%s[queued commands = %d, consumed = %d]%n", this,
				_queuedCount.intValue(), _consumedCount.intValue()));

		for (CommandMessage commandMessage : _commanMessageHistory) {
			sb.append(commandMessage);
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	public CommandMessage next() {
		CommandMessage commandMessage = _queue.poll();

		if (commandMessage != null) {
			_consumedCount.incrementAndGet();

			synchronized (_commanMessageHistory) {
				_commanMessageHistory.add(0, commandMessage);

				if (_commanMessageHistory.size() > 10) {
					_commanMessageHistory.remove(10);
				}
			}
		}

		return commandMessage;
	}

	@Activate
	protected void activate() {
		_consumedCount.set(0);
		_queuedCount.set(0);

		_queue.clear();

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_log.isTraceEnabled()) {
			_log.trace("Deactivate " + this);
		}

		_consumedCount.set(0);
		_queuedCount.set(0);

		if (!_queue.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%d command messages will be discarded on deactivation",
						_queue.size()));
			}

			_queue.clear();
		}
	}

	private void _add(Message message) {
		if (!(message instanceof CommandMessage)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to queue non command message " + message);

				return;
			}
		}

		_queue.add((CommandMessage)message);

		_queuedCount.incrementAndGet();
	}

	private static final Log _log = LogFactoryUtil.getLog(CommandQueue.class);

	private final List<CommandMessage> _commanMessageHistory = new ArrayList<>(
		10);
	private final AtomicLong _consumedCount = new AtomicLong();
	private final Queue<CommandMessage> _queue = new ArrayBlockingQueue<>(20);
	private final AtomicLong _queuedCount = new AtomicLong();

}