import PropTypes from 'prop-types';
import React from 'react';

import {langSub} from '../helpers/language';
import Alert from './Alert';

export default class CustomerPortalBanner extends React.Component {
	static propTypes = {
		customerPortalURL: PropTypes.string.isRequired
	};

	render() {
		return (
			<div className="customer-portal-banner container-fluid-max-md">
				<Alert type="info">
					{langSub(
						Liferay.Language.get(
							'visit-the-new-x-to-manage-your-projects-assign-team-members-and-activate-your-products-check-out-our-video-tutorials-to-get-started'
						),
						[
							<a
								aria-label={Liferay.Language.get('customer-portal-url')}
								className="btn-link"
								href={this.props.customerPortalURL}
							>
								{Liferay.Language.get('customer-portal')}
							</a>
						],
						false
					)}
				</Alert>
			</div>
		);
	}
}