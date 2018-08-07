import React from 'react';
import ReactDOM from 'react-dom';
import ErrorBoundary from '../components/error-boundary';

export default function reactRenderer(Component, props = {}, domContainerNode) {
	return ReactDOM.render(
		<ErrorBoundary>
			<Component {...props} />
		</ErrorBoundary>,
		domContainerNode
	);
}