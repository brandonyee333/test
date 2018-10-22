const path = require('path');

module.exports = {
	entry: './src/js/main.js',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				loader: 'babel-loader',
				test: /\.js$/
			}
		]
	},
	output: {
		filename: 'main.js',
		path: path.resolve('build/js')
	}
};