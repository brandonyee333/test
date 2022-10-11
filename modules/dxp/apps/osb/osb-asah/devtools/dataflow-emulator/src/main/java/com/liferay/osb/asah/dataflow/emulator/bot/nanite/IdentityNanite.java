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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite;

import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIdentityActivity;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.repository.BQIdentityActivityRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class IdentityNanite {

	public void run() throws Exception {
		while (true) {
			List<Message<String>> messages = _messageSubscriber.pullMessages(
				100, String::valueOf);

			if (messages.isEmpty()) {
				break;
			}

			Stream<Message<String>> stream = messages.stream();

			stream.forEach(this::_processMessage);

			_acknowledgeMessages(messages);
		}
	}

	private void _acknowledgeMessages(List<Message<String>> messages) {
		Stream<Message<String>> stream = messages.stream();

		_messageSubscriber.sendAckIds(
			stream.map(
				Message::getAckId
			).collect(
				Collectors.toList()
			));
	}

	private boolean _isBQIdentityActivityNew(String id) {
		Optional<BQIdentityActivity> optional =
			_bqIdentityActivityRepository.findById(id);

		return !optional.isPresent();
	}

	private boolean _isBQIdentityNew(String id) {
		Optional<BQIdentity> optional = _bqIdentityRepository.findById(id);

		return !optional.isPresent();
	}

	private void _processMessage(Message<String> message) {
		JSONObject jsonObject = new JSONObject(message.getObject());

		ProjectIdThreadLocal.forProject(
			jsonObject.getString("projectId"),
			() -> _saveBQIdentityActivity(
				_saveBQIdentity(jsonObject), jsonObject));
	}

	private BQIdentity _saveBQIdentity(JSONObject jsonObject) {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());

		String userId = jsonObject.getString("userId");

		bqIdentity.setId(userId);

		String individualId = jsonObject.getString("individualId");

		if (Objects.equals(individualId, _EMPTY_EMAIL_ADDRESS_HASHED)) {
			individualId = null;
		}

		bqIdentity.setIndividualId(individualId);

		bqIdentity.setIsNew(_isBQIdentityNew(userId));

		return _bqIdentityRepository.save(bqIdentity);
	}

	private BQIdentityActivity _saveBQIdentityActivity(
		BQIdentity bqIdentity, JSONObject jsonObject) {

		BQIdentityActivity bqIdentityActivity = new BQIdentityActivity();

		String channelId = jsonObject.getString("channelId");

		bqIdentityActivity.setChannelId(Long.valueOf(channelId));

		bqIdentityActivity.setCreateDate(new Date());

		String dataSourceId = jsonObject.getString("dataSourceId");

		bqIdentityActivity.setDataSourceId(Long.valueOf(dataSourceId));

		String id = String.join(
			"#", channelId, dataSourceId, bqIdentity.getId());

		bqIdentityActivity.setId(DigestUtils.sha256Hex(id));

		bqIdentityActivity.setIdentityId(bqIdentity.getId());
		bqIdentityActivity.setIndividualId(bqIdentity.getIndividualId());
		bqIdentityActivity.setIsNew(_isBQIdentityActivityNew(id));

		return _bqIdentityActivityRepository.save(bqIdentityActivity);
	}

	private static final String _EMPTY_EMAIL_ADDRESS_HASHED =
		"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

	@Autowired
	private BQIdentityActivityRepository _bqIdentityActivityRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@MessageSubscriber.Autowired(channel = Channel.IDENTITY_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}