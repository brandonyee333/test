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

import ClayCard from '@clayui/card';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import React, {useContext, useEffect, useState} from 'react';

import {AppContext} from '../../AppContext.es';
import Alert from '../../components/Alert.es';
import Link from '../../components/Link.es';
import {getSectionsFromRootSection} from '../../utils/client.es';
import lang from '../../utils/lang.es';

export default () => {
	const context = useContext(AppContext);

	const [error, setError] = useState({});
	const [loading, setLoading] = useState(true);
	const [sections, setSections] = useState({});

	useEffect(() => {
		getSectionsFromRootSection(context.rootTopic, context.siteKey)
			.then(({data, loading}) => {
				setSections(data || []);
				setLoading(loading);
			})
			.catch((error) => {
				if (process.env.NODE_ENV === 'development') {
					console.error(error);
				}
				setLoading(false);
				setError({message: 'Loading Topics', title: 'Error'});
			});
	}, [context.rootTopic, context.siteKey]);

	function descriptionTruncate(description) {
		return description.length > 150
			? description.substring(0, 150) + '...'
			: description;
	}

	return (
		<section className="questions-section questions-section-cards">
			<div className="questions-container">
				<div className="row">
					{!loading &&
						sections &&
						sections.items &&
						sections.items.map((section) => (
							<div
								className="c-mb-4 col-lg-4 col-md-6 col-xl-3"
								key={section.id}
							>
								<Link
									className="questions-card text-decoration-none text-secondary"
									to={`/questions/${section.title}`}
								>
									<ClayCard>
										<ClayCard.Body>
											<ClayCard.Description
												className="text-dark"
												displayType="title"
											>
												{section.title}
											</ClayCard.Description>

											<ClayCard.Description
												className="c-mt-3"
												displayType="text"
												truncate={false}
											>
												{descriptionTruncate(
													section.description
												)}
											</ClayCard.Description>

											<ClayCard.Description
												className="c-mt-4 small"
												displayType="text"
												truncate={false}
											>
												<span className="x-questions">
													{lang.sub(
														Liferay.Language.get(
															'x-questions'
														),
														[
															section.numberOfMessageBoardThreads,
														]
													)}
												</span>

												<button className="btn btn-link btn-sm d-xl-none float-right font-weight-bold p-0">
													View Topic
												</button>
											</ClayCard.Description>
										</ClayCard.Body>
									</ClayCard>
								</Link>
							</div>
						))}
				</div>
				{loading && <ClayLoadingIndicator />}

				<Alert info={error} />
			</div>
		</section>
	);
};
