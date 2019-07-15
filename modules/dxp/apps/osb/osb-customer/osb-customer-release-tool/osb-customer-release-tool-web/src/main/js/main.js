import 'es6-promise/auto';

import '../css/main.scss';

import React from 'react';
import ReactDOM from 'react-dom';

if (process.env.NODE_ENV === 'development') {
	import('react-axe').then(
		axe => {
			axe.default(React, ReactDOM, 1000);
		}
	);
}

export {default as render} from './helpers/react-renderer';

export {default as Changelog} from './components/Changelog';
export {default as FixpackFilters} from './components/FixpackFilters';
export {default as Highlights} from './components/Highlights';
export {default as ModuleChanges} from './components/ModuleChanges';