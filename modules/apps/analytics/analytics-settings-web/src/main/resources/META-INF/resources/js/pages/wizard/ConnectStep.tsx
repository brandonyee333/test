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

import ClayForm, {ClayInput} from '@clayui/form';
import React from 'react';

import BasePage from '../../components/BasePage';

const Step = () => {
	const handleSubmit = () => {};

	return (
		<BasePage
			description={Liferay.Language.get(
				'use-the-token-genereted-in-your-analytics-cloud-to-connect-this-workspace'
			)}
			title={Liferay.Language.get('connect-analytics-cloud')}
		>
			<ClayForm onSubmit={handleSubmit}>
				<ClayForm.Group>
					<label htmlFor="inputToken">
						{Liferay.Language.get('analytics-cloud-token')}
					</label>

					<ClayInput id="inputToken" type="text" value="text" />

					<small className="text-secondary">
						{Liferay.Language.get('analytics-cloud-token-help')}
					</small>
				</ClayForm.Group>
			</ClayForm>
		</BasePage>
	);
};

export default Step;
