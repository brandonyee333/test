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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.ExperimentStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * @author André Miranda
 */
public final class Experiment {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Experiment)) {
			return false;
		}

		Experiment experiment = (Experiment)obj;

		if (Objects.equals(_channelId, experiment._channelId) &&
			Objects.equals(_confidenceLevel, experiment._confidenceLevel) &&
			Objects.equals(_createDate, experiment._createDate) &&
			Objects.equals(_dataSourceId, experiment._dataSourceId) &&
			Objects.equals(_description, experiment._description) &&
			Objects.equals(_dxpExperienceId, experiment._dxpExperienceId) &&
			Objects.equals(_dxpExperienceName, experiment._dxpExperienceName) &&
			Objects.equals(_dxpGroupId, experiment._dxpGroupId) &&
			Objects.equals(_dxpLayoutId, experiment._dxpLayoutId) &&
			Objects.equals(_dxpSegmentId, experiment._dxpSegmentId) &&
			Objects.equals(_dxpSegmentName, experiment._dxpSegmentName) &&
			Objects.equals(_dxpVariants, experiment._dxpVariants) &&
			Objects.equals(_experimentStatus, experiment._experimentStatus) &&
			Objects.equals(_experimentType, experiment._experimentType) &&
			Objects.equals(_finishedDate, experiment._finishedDate) &&
			Objects.equals(_goal, experiment._goal) &&
			Objects.equals(_id, experiment._id) &&
			Objects.equals(_modifiedDate, experiment._modifiedDate) &&
			Objects.equals(_name, experiment._name) &&
			Objects.equals(_pageRelativePath, experiment._pageRelativePath) &&
			Objects.equals(_pageTitle, experiment._pageTitle) &&
			Objects.equals(_pageURL, experiment._pageURL) &&
			Objects.equals(_processedDate, experiment._processedDate) &&
			Objects.equals(
				_publishedDXPVariantId, experiment._publishedDXPVariantId) &&
			Objects.equals(_startedDate, experiment._startedDate) &&
			Objects.equals(
				_winnerDXPVariantId, experiment._winnerDXPVariantId)) {

			return true;
		}

		return false;
	}

	public String getChannelId() {
		return _channelId;
	}

	public Double getConfidenceLevel() {
		return _confidenceLevel;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@NotNull
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonIgnore
	public String getCreateDateISO() {
		if (_createDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_createDate);
	}

	@NotBlank
	public String getDataSourceId() {
		return _dataSourceId;
	}

	public String getDescription() {
		return _description;
	}

	@JsonProperty("dxpExperienceId")
	@NotBlank
	public String getDXPExperienceId() {
		return _dxpExperienceId;
	}

	@JsonProperty("dxpExperienceName")
	@NotBlank
	public String getDXPExperienceName() {
		return _dxpExperienceName;
	}

	@JsonProperty("dxpGroupId")
	public String getDXPGroupId() {
		return _dxpGroupId;
	}

	@JsonProperty("dxpLayoutId")
	@NotBlank
	public String getDXPLayoutId() {
		return _dxpLayoutId;
	}

	@JsonProperty("dxpSegmentId")
	@NotBlank
	public String getDXPSegmentId() {
		return _dxpSegmentId;
	}

	@JsonProperty("dxpSegmentName")
	@NotBlank
	public String getDXPSegmentName() {
		return _dxpSegmentName;
	}

	@JsonProperty("dxpVariants")
	@Valid
	public List<DXPVariant> getDXPVariants() {
		return _dxpVariants;
	}

	@JsonProperty("status")
	public ExperimentStatus getExperimentStatus() {
		return _experimentStatus;
	}

	@JsonProperty("type")
	public ExperimentType getExperimentType() {
		return _experimentType;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFinishedDate() {
		if (_finishedDate == null) {
			return null;
		}

		return new Date(_finishedDate.getTime());
	}

	@JsonIgnore
	public String getFinishedDateISO() {
		if (_finishedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_finishedDate);
	}

	@Valid
	public Goal getGoal() {
		return _goal;
	}

	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonIgnore
	public String getModifiedDateISO() {
		if (_modifiedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_modifiedDate);
	}

	@NotBlank
	public String getName() {
		return _name;
	}

	@NotBlank
	public String getPageRelativePath() {
		return _pageRelativePath;
	}

	public String getPageTitle() {
		return _pageTitle;
	}

	@NotBlank
	@URL
	public String getPageURL() {
		return _pageURL;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getProcessedDate() {
		if (_processedDate == null) {
			return null;
		}

		return new Date(_processedDate.getTime());
	}

	public String getPublishedDXPVariantId() {
		return _publishedDXPVariantId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getStartedDate() {
		if (_startedDate == null) {
			return null;
		}

		return new Date(_startedDate.getTime());
	}

	@JsonIgnore
	public String getStartedDateISO() {
		if (_startedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_startedDate);
	}

	@JsonIgnore
	public LocalDateTime getStartedDateLocalDateTime() {
		if (_startedDate == null) {
			return null;
		}

		return DateUtil.toLocalDateTime(_startedDate, ZoneOffset.UTC);
	}

	public String getWinnerDXPVariantId() {
		return _winnerDXPVariantId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_channelId, _confidenceLevel, _createDate, _dataSourceId,
			_description, _dxpExperienceId, _dxpExperienceName, _dxpGroupId,
			_dxpLayoutId, _dxpSegmentId, _dxpSegmentName, _dxpVariants,
			_experimentStatus, _experimentType, _finishedDate, _goal, _id,
			_modifiedDate, _name, _pageRelativePath, _pageTitle, _pageURL,
			_processedDate, _publishedDXPVariantId, _startedDate,
			_winnerDXPVariantId);
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setConfidenceLevel(Double confidenceLevel) {
		_confidenceLevel = confidenceLevel;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceId(String dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDXPExperienceId(String dxpExperienceId) {
		_dxpExperienceId = dxpExperienceId;
	}

	public void setDXPExperienceName(String dxpExperienceName) {
		_dxpExperienceName = dxpExperienceName;
	}

	public void setDXPGroupId(String dxpGroupId) {
		_dxpGroupId = dxpGroupId;
	}

	public void setDXPLayoutId(String dxpLayoutId) {
		_dxpLayoutId = dxpLayoutId;
	}

	public void setDXPSegmentId(String dxpSegmentId) {
		_dxpSegmentId = dxpSegmentId;
	}

	public void setDXPSegmentName(String dxpSegmentName) {
		_dxpSegmentName = dxpSegmentName;
	}

	public void setDXPVariants(List<DXPVariant> dxpVariants) {
		_dxpVariants = dxpVariants;
	}

	public void setExperimentStatus(ExperimentStatus experimentStatus) {
		_experimentStatus = experimentStatus;
	}

	public void setExperimentType(ExperimentType experimentType) {
		_experimentType = experimentType;
	}

	public void setFinishedDate(Date finishedDate) {
		if (finishedDate != null) {
			_finishedDate = new Date(finishedDate.getTime());
		}
	}

	public void setGoal(Goal goal) {
		_goal = goal;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPageRelativePath(String pageRelativePath) {
		_pageRelativePath = pageRelativePath;
	}

	public void setPageTitle(String pageTitle) {
		_pageTitle = pageTitle;
	}

	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	public void setProcessedDate(Date processedDate) {
		if (processedDate != null) {
			_processedDate = new Date(processedDate.getTime());
		}
	}

	public void setPublishedDXPVariantId(String publishedDXPVariantId) {
		_publishedDXPVariantId = publishedDXPVariantId;
	}

	public void setStartedDate(Date startedDate) {
		if (startedDate != null) {
			_startedDate = new Date(startedDate.getTime());
		}
	}

	public void setWinnerDXPVariantId(String winnerDXPVariantId) {
		_winnerDXPVariantId = winnerDXPVariantId;
	}

	private String _channelId;
	private Double _confidenceLevel;
	private Date _createDate;
	private String _dataSourceId;
	private String _description;
	private String _dxpExperienceId;
	private String _dxpExperienceName;
	private String _dxpGroupId;
	private String _dxpLayoutId;
	private String _dxpSegmentId;
	private String _dxpSegmentName;
	private List<DXPVariant> _dxpVariants;
	private ExperimentStatus _experimentStatus = ExperimentStatus.DRAFT;
	private ExperimentType _experimentType = ExperimentType.AB;
	private Date _finishedDate;
	private Goal _goal;
	private String _id;
	private Date _modifiedDate;
	private String _name;
	private String _pageRelativePath;
	private String _pageTitle;
	private String _pageURL;
	private Date _processedDate;
	private String _publishedDXPVariantId;
	private Date _startedDate;
	private String _winnerDXPVariantId;

}