import React from 'react';
import ReactDOM from 'react-dom';

export default function reactRenderer(Component, props = {}, domContainerNode) {
	return ReactDOM.render(<Component {...props} />, domContainerNode);
}