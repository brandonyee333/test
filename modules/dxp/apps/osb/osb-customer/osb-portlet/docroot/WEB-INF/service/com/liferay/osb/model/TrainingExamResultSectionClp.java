/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.TrainingExamResultSectionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingExamResultSectionClp extends BaseModelImpl<TrainingExamResultSection>
	implements TrainingExamResultSection {
	public TrainingExamResultSectionClp() {
	}

	public Class<?> getModelClass() {
		return TrainingExamResultSection.class;
	}

	public String getModelClassName() {
		return TrainingExamResultSection.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingExamResultSectionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingExamResultSectionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingExamResultSectionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultSectionId",
			getTrainingExamResultSectionId());
		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("title", getTitle());
		attributes.put("sectionKey", getSectionKey());
		attributes.put("scoreIndicator", getScoreIndicator());
		attributes.put("scoringAlgorithm", getScoringAlgorithm());
		attributes.put("masteryScore", getMasteryScore());
		attributes.put("score", getScore());
		attributes.put("standardErrorOfEstimate", getStandardErrorOfEstimate());
		attributes.put("correctCount", getCorrectCount());
		attributes.put("incorrectCount", getIncorrectCount());
		attributes.put("skippedCount", getSkippedCount());
		attributes.put("grade", getGrade());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultSectionId = (Long)attributes.get(
				"trainingExamResultSectionId");

		if (trainingExamResultSectionId != null) {
			setTrainingExamResultSectionId(trainingExamResultSectionId);
		}

		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String sectionKey = (String)attributes.get("sectionKey");

		if (sectionKey != null) {
			setSectionKey(sectionKey);
		}

		Boolean scoreIndicator = (Boolean)attributes.get("scoreIndicator");

		if (scoreIndicator != null) {
			setScoreIndicator(scoreIndicator);
		}

		String scoringAlgorithm = (String)attributes.get("scoringAlgorithm");

		if (scoringAlgorithm != null) {
			setScoringAlgorithm(scoringAlgorithm);
		}

		String masteryScore = (String)attributes.get("masteryScore");

		if (masteryScore != null) {
			setMasteryScore(masteryScore);
		}

		String score = (String)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		String standardErrorOfEstimate = (String)attributes.get(
				"standardErrorOfEstimate");

		if (standardErrorOfEstimate != null) {
			setStandardErrorOfEstimate(standardErrorOfEstimate);
		}

		Integer correctCount = (Integer)attributes.get("correctCount");

		if (correctCount != null) {
			setCorrectCount(correctCount);
		}

		Integer incorrectCount = (Integer)attributes.get("incorrectCount");

		if (incorrectCount != null) {
			setIncorrectCount(incorrectCount);
		}

		Integer skippedCount = (Integer)attributes.get("skippedCount");

		if (skippedCount != null) {
			setSkippedCount(skippedCount);
		}

		Integer grade = (Integer)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}
	}

	public long getTrainingExamResultSectionId() {
		return _trainingExamResultSectionId;
	}

	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultSectionId = trainingExamResultSectionId;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultSectionId",
						long.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					trainingExamResultSectionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingExamResultId",
						long.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					trainingExamResultId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setTitle", String.class);

				method.invoke(_trainingExamResultSectionRemoteModel, title);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getSectionKey() {
		return _sectionKey;
	}

	public void setSectionKey(String sectionKey) {
		_sectionKey = sectionKey;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSectionKey", String.class);

				method.invoke(_trainingExamResultSectionRemoteModel, sectionKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getScoreIndicator() {
		return _scoreIndicator;
	}

	public boolean isScoreIndicator() {
		return _scoreIndicator;
	}

	public void setScoreIndicator(boolean scoreIndicator) {
		_scoreIndicator = scoreIndicator;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setScoreIndicator",
						boolean.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					scoreIndicator);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScoringAlgorithm() {
		return _scoringAlgorithm;
	}

	public void setScoringAlgorithm(String scoringAlgorithm) {
		_scoringAlgorithm = scoringAlgorithm;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setScoringAlgorithm",
						String.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					scoringAlgorithm);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getMasteryScore() {
		return _masteryScore;
	}

	public void setMasteryScore(String masteryScore) {
		_masteryScore = masteryScore;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMasteryScore", String.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					masteryScore);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScore() {
		return _score;
	}

	public void setScore(String score) {
		_score = score;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setScore", String.class);

				method.invoke(_trainingExamResultSectionRemoteModel, score);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getStandardErrorOfEstimate() {
		return _standardErrorOfEstimate;
	}

	public void setStandardErrorOfEstimate(String standardErrorOfEstimate) {
		_standardErrorOfEstimate = standardErrorOfEstimate;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setStandardErrorOfEstimate",
						String.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					standardErrorOfEstimate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getCorrectCount() {
		return _correctCount;
	}

	public void setCorrectCount(int correctCount) {
		_correctCount = correctCount;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCorrectCount", int.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					correctCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getIncorrectCount() {
		return _incorrectCount;
	}

	public void setIncorrectCount(int incorrectCount) {
		_incorrectCount = incorrectCount;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setIncorrectCount", int.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					incorrectCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSkippedCount() {
		return _skippedCount;
	}

	public void setSkippedCount(int skippedCount) {
		_skippedCount = skippedCount;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSkippedCount", int.class);

				method.invoke(_trainingExamResultSectionRemoteModel,
					skippedCount);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getGrade() {
		return _grade;
	}

	public void setGrade(int grade) {
		_grade = grade;

		if (_trainingExamResultSectionRemoteModel != null) {
			try {
				Class<?> clazz = _trainingExamResultSectionRemoteModel.getClass();

				Method method = clazz.getMethod("setGrade", int.class);

				method.invoke(_trainingExamResultSectionRemoteModel, grade);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getResult() {
		try {
			String methodName = "getResult";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getTrainingExamResultSectionRemoteModel() {
		return _trainingExamResultSectionRemoteModel;
	}

	public void setTrainingExamResultSectionRemoteModel(
		BaseModel<?> trainingExamResultSectionRemoteModel) {
		_trainingExamResultSectionRemoteModel = trainingExamResultSectionRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _trainingExamResultSectionRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_trainingExamResultSectionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingExamResultSectionLocalServiceUtil.addTrainingExamResultSection(this);
		}
		else {
			TrainingExamResultSectionLocalServiceUtil.updateTrainingExamResultSection(this);
		}
	}

	@Override
	public TrainingExamResultSection toEscapedModel() {
		return (TrainingExamResultSection)ProxyUtil.newProxyInstance(TrainingExamResultSection.class.getClassLoader(),
			new Class[] { TrainingExamResultSection.class },
			new AutoEscapeBeanHandler(this));
	}

	public TrainingExamResultSection toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingExamResultSectionClp clone = new TrainingExamResultSectionClp();

		clone.setTrainingExamResultSectionId(getTrainingExamResultSectionId());
		clone.setTrainingExamResultId(getTrainingExamResultId());
		clone.setTitle(getTitle());
		clone.setSectionKey(getSectionKey());
		clone.setScoreIndicator(getScoreIndicator());
		clone.setScoringAlgorithm(getScoringAlgorithm());
		clone.setMasteryScore(getMasteryScore());
		clone.setScore(getScore());
		clone.setStandardErrorOfEstimate(getStandardErrorOfEstimate());
		clone.setCorrectCount(getCorrectCount());
		clone.setIncorrectCount(getIncorrectCount());
		clone.setSkippedCount(getSkippedCount());
		clone.setGrade(getGrade());

		return clone;
	}

	public int compareTo(TrainingExamResultSection trainingExamResultSection) {
		int value = 0;

		value = getSectionKey()
					.compareTo(trainingExamResultSection.getSectionKey());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingExamResultSectionClp)) {
			return false;
		}

		TrainingExamResultSectionClp trainingExamResultSection = (TrainingExamResultSectionClp)obj;

		long primaryKey = trainingExamResultSection.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{trainingExamResultSectionId=");
		sb.append(getTrainingExamResultSectionId());
		sb.append(", trainingExamResultId=");
		sb.append(getTrainingExamResultId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", sectionKey=");
		sb.append(getSectionKey());
		sb.append(", scoreIndicator=");
		sb.append(getScoreIndicator());
		sb.append(", scoringAlgorithm=");
		sb.append(getScoringAlgorithm());
		sb.append(", masteryScore=");
		sb.append(getMasteryScore());
		sb.append(", score=");
		sb.append(getScore());
		sb.append(", standardErrorOfEstimate=");
		sb.append(getStandardErrorOfEstimate());
		sb.append(", correctCount=");
		sb.append(getCorrectCount());
		sb.append(", incorrectCount=");
		sb.append(getIncorrectCount());
		sb.append(", skippedCount=");
		sb.append(getSkippedCount());
		sb.append(", grade=");
		sb.append(getGrade());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingExamResultSection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingExamResultSectionId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultSectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingExamResultId</column-name><column-value><![CDATA[");
		sb.append(getTrainingExamResultId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sectionKey</column-name><column-value><![CDATA[");
		sb.append(getSectionKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scoreIndicator</column-name><column-value><![CDATA[");
		sb.append(getScoreIndicator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scoringAlgorithm</column-name><column-value><![CDATA[");
		sb.append(getScoringAlgorithm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>masteryScore</column-name><column-value><![CDATA[");
		sb.append(getMasteryScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>score</column-name><column-value><![CDATA[");
		sb.append(getScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>standardErrorOfEstimate</column-name><column-value><![CDATA[");
		sb.append(getStandardErrorOfEstimate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>correctCount</column-name><column-value><![CDATA[");
		sb.append(getCorrectCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>incorrectCount</column-name><column-value><![CDATA[");
		sb.append(getIncorrectCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>skippedCount</column-name><column-value><![CDATA[");
		sb.append(getSkippedCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>grade</column-name><column-value><![CDATA[");
		sb.append(getGrade());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingExamResultSectionId;
	private long _trainingExamResultId;
	private String _title;
	private String _sectionKey;
	private boolean _scoreIndicator;
	private String _scoringAlgorithm;
	private String _masteryScore;
	private String _score;
	private String _standardErrorOfEstimate;
	private int _correctCount;
	private int _incorrectCount;
	private int _skippedCount;
	private int _grade;
	private BaseModel<?> _trainingExamResultSectionRemoteModel;
}