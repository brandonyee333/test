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

package com.liferay.osb.asah.stream.curator.bot.nanite.identity;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.storage.v1.AppendRowsResponse;
import com.google.cloud.bigquery.storage.v1.BigQueryWriteClient;
import com.google.cloud.bigquery.storage.v1.CreateWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.FinalizeWriteStreamRequest;
import com.google.cloud.bigquery.storage.v1.JsonStreamWriter;
import com.google.cloud.bigquery.storage.v1.TableName;
import com.google.cloud.bigquery.storage.v1.WriteStream;
import com.google.common.util.concurrent.MoreExecutors;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class IdentityBigQueryIngestionNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "identities";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _acknowledgeMessages(List<Message<JSONObject>> messages) {
		List<String> ids = new ArrayList<>();

		for (Message<JSONObject> message : messages) {
			ids.add(message.getAckId());
		}

		_messageSubscriber.sendAckIds(ids);
	}

	private CreateWriteStreamRequest _buildCreateWriteStreamRequest(
		String datasetName) {

		CreateWriteStreamRequest.Builder builder =
			CreateWriteStreamRequest.newBuilder();

		return builder.setParent(
			String.valueOf(
				TableName.of(_googleProjectId, datasetName, _TABLE_NAME))
		).setWriteStream(
			WriteStream.newBuilder(
			).setType(
				WriteStream.Type.COMMITTED
			).build()
		).build();
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_googleProjectId = bigQueryOptions.getProjectId();
	}

	private void _insertIntoBigQueryTable(
			String datasetName, List<Message<JSONObject>> messages)
		throws Exception {

		try (BigQueryWriteClient bigQueryWriteClient =
				BigQueryWriteClient.create()) {

			WriteStream clientWriteStream =
				bigQueryWriteClient.createWriteStream(
					_buildCreateWriteStreamRequest(datasetName));

			try (JsonStreamWriter jsonStreamWriter =
					JsonStreamWriter.newBuilder(
						clientWriteStream.getName(),
						clientWriteStream.getTableSchema()
					).build()) {

				ApiFuture<AppendRowsResponse> apiFuture =
					jsonStreamWriter.append(
						JSONUtil.toJSONArray(messages, this::_toJSONObject));

				ApiFutures.addCallback(
					apiFuture,
					new ApiFutureCallback<AppendRowsResponse>() {

						@Override
						public void onFailure(Throwable throwable) {
							_log.error(throwable, throwable);
						}

						@Override
						public void onSuccess(
							AppendRowsResponse appendRowsResponse) {

							if (_log.isInfoEnabled()) {
								_log.info(
									String.format(
										"%s: %d rows inserted into identity " +
											"table",
										datasetName, messages.size()));
							}
						}

					},
					MoreExecutors.directExecutor());

				FinalizeWriteStreamRequest finalizeWriteStreamRequest =
					FinalizeWriteStreamRequest.newBuilder(
					).setName(
						clientWriteStream.getName()
					).build();

				bigQueryWriteClient.finalizeWriteStream(
					finalizeWriteStreamRequest);
			}
		}
	}

	private void _run() throws Exception {
		while (true) {
			List<Message<JSONObject>> messages =
				_messageSubscriber.pullMessages(
					_identityNanitePullMessagesSize, JSONObject::new);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"%d identity messages received", messages.size()));
			}

			if (messages.isEmpty()) {
				break;
			}

			Stream<Message<JSONObject>> stream = messages.stream();

			Map<String, List<Message<JSONObject>>> messagesMap = stream.collect(
				Collectors.groupingBy(
					message -> {
						JSONObject jsonObject = message.getObject();

						return jsonObject.getString("projectId");
					}));

			for (Map.Entry<String, List<Message<JSONObject>>> entry :
					messagesMap.entrySet()) {

				_insertIntoBigQueryTable(entry.getKey(), entry.getValue());
			}

			_acknowledgeMessages(messages);
		}
	}

	private JSONObject _toJSONObject(Message<JSONObject> message) {
		JSONObject jsonObject = message.getObject();

		jsonObject.put("createDate", new Date());
		jsonObject.put(
			"id",
			DigestUtils.sha256Hex(
				String.join(
					"#", jsonObject.getString("projectId"),
					jsonObject.getString("dataSourceId"),
					jsonObject.getString("channelId"),
					jsonObject.getString("emailAddressHashed"))));

		return jsonObject;
	}

	private static final String _TABLE_NAME = "identity";

	private static final Log _log = LogFactory.getLog(
		IdentityBigQueryIngestionNanite.class);

	private String _googleProjectId;

	@Value("${osb.asah.identity.nanite.pull.messages.size:1000}")
	private int _identityNanitePullMessagesSize;

	@MessageSubscriber.Autowired(channel = Channel.IDENTITY_MESSAGE)
	private MessageSubscriber _messageSubscriber;

}