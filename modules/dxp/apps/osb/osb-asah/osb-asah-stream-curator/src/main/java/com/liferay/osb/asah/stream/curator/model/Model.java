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

package com.liferay.osb.asah.stream.curator.model;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;

/**
 * @author Marcellus Tavares
 * @author Brian Wing Shun Chan
 */
public interface Model extends Serializable {

	public void addSegmentNames(Set<String> segmentNames);

	public String getBrowserName();

	public String getChannelId();

	public String getCity();

	public String getContentLanguageId();

	public String getCountry();

	public String getDataSourceId();

	public String getDeviceType();

	public Date getEventDate();

	public String getExperienceId();

	public String getExperimentId();

	public String getId();

	public String getIndividualId();

	public Date getLastEventDate();

	public Date getModifiedDate();

	public String getPlatformName();

	public String getPrimaryKey();

	public String getRegion();

	public Set<String> getSegmentNames();

	public String getSessionId();

	public String getTitle();

	public String getUserId();

	public String getVariantId();

	public boolean isAsset();

	public boolean isKnownIndividual();

	public void setBrowserName(String browserName);

	public void setChannelId(String channelId);

	public void setCity(String city);

	public void setContentLanguageId(String contentLanguageId);

	public void setCountry(String country);

	public void setDataSourceId(String dataSourceId);

	public void setDeviceType(String deviceType);

	public void setEventDate(Date eventDate);

	public void setExperienceId(String experienceId);

	public void setExperimentId(String experimentId);

	public void setId(String id);

	public void setIndividualId(String individualId);

	public void setKnownIndividual(boolean knownIndividual);

	public void setLastEventDate(Date lastEventDate);

	public void setModifiedDate(Date modifiedDate);

	public void setPlatformName(String platformName);

	public void setPrimaryKey(String primaryKey);

	public void setRegion(String region);

	public void setSegmentNames(Set<String> segmentNames);

	public void setSessionId(String sessionId);

	public void setTitle(String title);

	public void setUserId(String userId);

	public void setVariantId(String experimentId);

}