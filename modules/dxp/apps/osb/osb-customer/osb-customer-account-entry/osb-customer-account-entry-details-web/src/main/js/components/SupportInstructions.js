import React from 'react';
import PropTypes from 'prop-types';

export default class SupportInstructions extends React.Component {
	static propTypes = {
		instructions: PropTypes.string.isRequired
	};

	render() {
		const {instructions} = this.props;

		return (
			<React.Fragment>
				<div className="card-header small-title">
					{Liferay.Language.get('support-instructions')}
				</div>

				{instructions ? (
					<div className="card-body">{instructions}</div>
				) : (
					<div className="no-results">
						{Liferay.Language.get('no-support-instructions')}
					</div>
				)}
			</React.Fragment>
		);
	}
}
