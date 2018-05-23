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

package com.liferay.lcs.messaging.analytics;

/**
 * @author Riccardo Ferrari
 */
public class ScreensAnalyticsEventsMessage extends AnalyticsEventsMessage {

	public static class Context extends AnalyticsEventsMessage.Context {

		public String getDeviceId() {
			return _deviceId;
		}

		public String getDeviceType() {
			return _deviceType;
		}

		public boolean isSignedIn() {
			return _signedIn;
		}

		public void setDeviceId(String deviceId) {
			_deviceId = deviceId;
		}

		public void setDeviceType(String deviceType) {
			_deviceType = deviceType;
		}

		public void setSignedIn(boolean signedIn) {
			_signedIn = signedIn;
		}

		private String _deviceId;
		private String _deviceType;
		private boolean _signedIn;

	}

	public static class Event extends AnalyticsEventsMessage.Event {

		public long getGroupId() {
			return _groupId;
		}

		public void setGroupId(long groupId) {
			_groupId = groupId;
		}

		private long _groupId;

	}

	public static class Referrer extends AnalyticsEventsMessage.Referrer {

		public String getElementId() {
			return _elementId;
		}

		public void setElementId(String elementId) {
			_elementId = elementId;
		}

		private String _elementId;

	}

}