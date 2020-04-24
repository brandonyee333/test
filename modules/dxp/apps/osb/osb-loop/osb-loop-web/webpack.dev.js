const common = require('./webpack.common.js');
const merge = require('webpack-merge');
const webpack = require('webpack');

module.exports = merge(
	common.config,
	{
		devServer: {
			port: 3000,
			proxy: {
				'**': 'http://0.0.0.0:8080'
			},
			publicPath: common.PUBLIC_PATH
		},
		devtool: 'inline-source-map',
		module: {
			rules: [
				{
					exclude: /node_modules/,
					loader: require.resolve('./lang-key-dev-loader'),
					test: /\.js$/
				}
			]
		},
		plugins: [
			new webpack.DefinePlugin(
				{
					'process.env': {
						NODE_ENV: '"development"'
					}
				}
			)
		]
	}
);