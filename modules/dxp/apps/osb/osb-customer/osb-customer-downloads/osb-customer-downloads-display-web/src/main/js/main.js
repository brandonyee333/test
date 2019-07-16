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

export {default as FileDownloads} from './components/FileDownloads';
export {default as SearchFilter} from './components/SearchFilter';