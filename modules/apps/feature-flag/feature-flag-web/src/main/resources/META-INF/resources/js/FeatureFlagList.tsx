/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayEmptyState from '@clayui/empty-state';
import {sub} from 'frontend-js-web';
import React, {useState} from 'react';

import FeatureFlagToggle from './FeatureFlagToggle';

import './css/main.scss';

export type TFeatureFlags = {
	companyId: number;
	dependenciesFulfilled: boolean;
	dependencyKeys: Array<string>;
	description: string;
	enabled: boolean;
	key: string;
	title: string;
};

interface Props {
	featureFlags: Array<TFeatureFlags>;
}

const FeatureFlagList: React.FC<Props> = ({featureFlags}) => {
	const [items, setItems] = useState<TFeatureFlags[]>(featureFlags);

	const displayFeatureFlag = (items: TFeatureFlags[]) =>
		items.map(
			({
				companyId,
				dependenciesFulfilled,
				dependencyKeys,
				description,
				enabled,
				key,
				title,
			}: TFeatureFlags) => {
				const dependency = dependencyKeys.map((dep) => dep);

				return (
					<div
						className="align-items-start d-flex feature-flags-container justify-content-between"
						key={key}
					>
						<div>
							<h5>
								<strong>{title}</strong>

								<span className="text-muted"> ({key})</span>
							</h5>

							<h6 className="mb-1">{description}</h6>

							{!!dependencyKeys.length && (
								<h6>
									{sub(
										Liferay.Language.get('dependencies-x'),
										[(dependency as unknown) as string]
									)}
								</h6>
							)}
						</div>

						<FeatureFlagToggle
							ariaDescribedBy={title}
							companyId={companyId}
							disabled={!dependenciesFulfilled}
							featureFlagKey={key}
							inputName={key}
							labelOff={Liferay.Language.get('disabled')}
							labelOn={Liferay.Language.get('enabled')}
							onItemsChange={(newItems) => {
								setItems((items) =>
									items.map((item) => {
										const newItem = newItems.find(
											(newItem) => {
												return newItem.key === item.key;
											}
										);

										if (newItem) {
											return newItem;
										}

										return item;
									})
								);
							}}
							toggled={enabled}
						/>
					</div>
				);
			}
		);

	return (
		<>
			{!items.length && (
				<ClayEmptyState
					description={Liferay.Language.get(
						'no-feature-flags-were-found'
					)}
					imgProps={{alt: 'no-feature-flags-were-found'}}
					imgSrc={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/search_state.gif`}
					imgSrcReducedMotion={`${Liferay.ThemeDisplay.getPathThemeImages()}/states/empty_state_reduced_motion.gif`}
				/>
			)}

			{!!items.length && displayFeatureFlag(items)}
		</>
	);
};

export default FeatureFlagList;
