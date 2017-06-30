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

package com.liferay.osb.training.importer;

import com.liferay.osb.util.PortletPropsValues;

/**
 * @author Haote Chou
 */
public class TrainingExamResultsImporterFactoryUtil {

	public static TrainingExamResultsImporter
		createTrainingExamResultsImporter() throws Exception {

		return createTrainingExamResultsImporter(
			PortletPropsValues.TRAINING_EXAM_RESULTS_IMPORTER);
	}

	public static TrainingExamResultsImporter createTrainingExamResultsImporter(
			String trainingExamResultsImporterName)
		throws Exception {

		if (trainingExamResultsImporterName.equals(
				TrainingExamResultsImporterConstants.
					TRAINING_EXAM_RESULTS_IMPORTER_NAME_KRYTERION)) {

			return new KryterionTrainingExamResultsImporterImpl();
		}
		else if (trainingExamResultsImporterName.equals(
					TrainingExamResultsImporterConstants.
						TRAINING_EXAM_RESULTS_IMPORTER_NAME_PROMETRIC)) {

			return new PrometricTrainingExamResultsImporterImpl();
		}

		throw new TrainingExamResultsImporterException(
			"Training exam results importer is not found");
	}

}