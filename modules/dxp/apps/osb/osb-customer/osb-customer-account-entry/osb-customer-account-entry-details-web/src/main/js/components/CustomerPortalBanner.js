import React from 'react';
import PropTypes from "prop-types";

import Alert from './Alert';
import {langSub} from '../helpers/language';

export default class CustomerPortalBanner extends React.Component {
	static propTypes = {
		customerPortalURL: PropTypes.string.isRequired,
		videoTutorialURL: PropTypes.string.isRequired
	};

	render() {
		return (
			<div>
				<Alert type="info">
					{langSub(
						Liferay.Language.get(
							'visit-the-new-x-to-manage-your-projects-assign-team-members-and-activate-your-products-check-out-x-to-get-started'
						),
						[
							<a
								aria-label={Liferay.Language.get('customer-portal-url')}
								className="btn-link"
								href={this.props.customerPortalURL}
							>
								{Liferay.Language.get('customer-portal')}
							</a>,
							<a
								aria-label={Liferay.Language.get('video-tutorial-url')}
								className="btn-link"
								href={this.props.videoTutorialURL}
							>
								{Liferay.Language.get('our-video-tutorials')}
							</a>
						],
						false
					)}
				</Alert>
			</div>
		);
	}
}