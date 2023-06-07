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

package com.liferay.osb.asah.dataflow.ingestion.dxp.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;

import java.nio.charset.StandardCharsets;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;

import org.codehaus.jackson.map.util.ISO8601DateFormat;

/**
 * @author Riccardo Ferrari
 */
@JsonDeserialize(builder = DXPEntityPubsubMessage.Builder.class)
public class DXPEntityPubsubMessage implements Serializable {

	public DXPEntityPubsubMessage(
		Map<String, String> attributes, String payload) {

		_attributes = new Attributes(attributes);
		_payload = payload;
	}

	public DXPEntityPubsubMessage(PubsubMessage pubsubMessage) {
		_attributes = new Attributes(pubsubMessage.getAttributeMap());
		_payload = new String(
			pubsubMessage.getPayload(), StandardCharsets.UTF_8);
	}

	public Attributes getAttributes() {
		return _attributes;
	}

	public String getPayload() {
		return _payload;
	}

	public static class Attributes extends HashMap<String, String> {

		public Attributes(Map<String, String> attributes) {
			if (attributes != null) {
				putAll(attributes);
			}
		}

		public Map<Long, Long> getCommerceChannelIdChannelIds()
			throws Exception {

			String commerceChannelIdChannelIdsString = getOrDefault(
				"commerceChannelIdChannelIds", null);

			if (commerceChannelIdChannelIdsString == null) {
				return Collections.emptyMap();
			}

			Map<String, Long> commerceChannelIdChannelIdsJSONFormatted =
				_objectMapper.readValue(
					commerceChannelIdChannelIdsString, Map.class);

			Map<Long, Long> commerceChannelIdChannelIds = new HashMap<>();

			for (Entry<String, Long> entry :
					commerceChannelIdChannelIdsJSONFormatted.entrySet()) {

				commerceChannelIdChannelIds.put(
					Long.parseLong(entry.getKey()), entry.getValue());
			}

			return commerceChannelIdChannelIds;
		}

		public long getCount() {
			return Long.parseLong(getOrDefault("count", "0"));
		}

		public String getDataSourceId() {
			return get("dataSourceId");
		}

		public String getProjectId() {
			return get("projectId");
		}

		public String getResourceName() {
			return get("resourceName");
		}

		public String getUploadTime() {
			return get("uploadTime");
		}

		public String getUploadType() {
			return get("uploadType");
		}

		public boolean isLast() {
			return Boolean.parseBoolean(getOrDefault("last", "false"));
		}

		private static final ObjectMapper _objectMapper;

		static {
			_objectMapper = new ObjectMapper() {
				{
					configure(
						DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
					configure(
						DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,
						true);
					configure(DeserializationFeature.USE_LONG_FOR_INTS, true);
					configure(
						MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
					configure(
						SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
					setDateFormat(new ISO8601DateFormat());
					setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
					setSerializationInclusion(JsonInclude.Include.NON_NULL);
					setVisibility(
						PropertyAccessor.FIELD,
						JsonAutoDetect.Visibility.PUBLIC_ONLY);
					setVisibility(
						PropertyAccessor.GETTER,
						JsonAutoDetect.Visibility.PUBLIC_ONLY);
				}
			};
		}

	}

	@JsonPOJOBuilder
	public static class Builder {

		public DXPEntityPubsubMessage build() {
			return new DXPEntityPubsubMessage(_attributes, _payload);
		}

		public Builder withAttributes(Map<String, String> attributes) {
			_attributes = attributes;

			return this;
		}

		public Builder withPayload(String payload) {
			_payload = payload;

			return this;
		}

		private Map<String, String> _attributes;
		private String _payload;

	}

	private final Attributes _attributes;
	private final String _payload;

}