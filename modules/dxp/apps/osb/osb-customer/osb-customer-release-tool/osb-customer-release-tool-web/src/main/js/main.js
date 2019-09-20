import 'es6-promise/auto';

import '../css/main.scss';

if (process.env.NODE_ENV === 'development') {
	const axe = require('react-axe');
	const React = require('react');
	const ReactDOM = require('react-dom');

	axe(React, ReactDOM, 1000);
}

export {default as render} from './helpers/react-renderer';

export {default as Changelog} from './components/Changelog';
export {default as FixpackFilters} from './components/FixpackFilters';
export {default as Highlights} from './components/Highlights';
export {default as ModuleChanges} from './components/ModuleChanges';