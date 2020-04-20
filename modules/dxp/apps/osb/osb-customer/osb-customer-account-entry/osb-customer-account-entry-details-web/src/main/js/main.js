import 'es6-promise/auto';

import '../css/main.scss';

if (process.env.NODE_ENV === 'development') {
	const axe = require('react-axe');
	const React = require('react');
	const ReactDOM = require('react-dom');

	axe(React, ReactDOM, 1000);
}

export {default as render} from './helpers/react-renderer';

export {default as AccountEnvironments} from './components/AccountEnvironments';
export {default as AddTicketAttachment} from './components/AddTicketAttachment';
export {default as SourceCodeAccess} from './components/SourceCodeAccess';
export {default as SupportInstructions} from './components/SupportInstructions';