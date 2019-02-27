import React from 'react';
import PropTypes from 'prop-types';

export default class ErrorBoundary extends React.Component {
	static propTypes = {
		children: PropTypes.any
	};

	state = {
		error: '',
		hasError: false,
		info: ''
	};

	componentDidCatch(error, info) {
		this.setState(
			{
				error,
				hasError: true,
				info
			}
		);

		if (process.env.NODE_ENV === 'development') {
			console.log(`Error: ${error}`);
			console.log(`Error Info: ${JSON.stringify(info)}`);
		}
	}

	render() {
		return this.state.hasError ? (
			<div className="alert alert-danger" role="alert">
				{Liferay.Language.get('your-request-failed-to-complete')}
			</div>
		) : (
			this.props.children
		);
	}
}