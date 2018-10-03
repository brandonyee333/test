const merge = require('webpack-merge');
const webpack = require('webpack');

const common = require('./webpack.common.js');

module.exports = merge(
	common,
	{
		devServer: {
			hot: true,
			port: 3000,
			proxy: {
				'**': 'http://0.0.0.0:8080'
			},
			publicPath: common.PUBLIC_PATH
		},
		devtool: 'inline-source-map',
		mode: 'development',
		module: {
			rules: [
				{
					loader: 'liferay-lang-key-dev-loader',
					test: /\.js$/
				}
			]
		},
		plugins: [new webpack.HotModuleReplacementPlugin()]
	}
);