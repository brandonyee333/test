const CleanWebpackPlugin = require('clean-webpack-plugin');
const common = require('./webpack.common.js');
const merge = require('webpack-merge');
const path = require('path');
const webpack = require('webpack');

const email = require('./webpack.email.js');

const prod = merge(
	common.config,
	{
		plugins: [
			new CleanWebpackPlugin([path.resolve(__dirname, common.bundleDirectory + 'dist')]),
			new webpack.DefinePlugin(
				{
					'process.env': {
						NODE_ENV: '"production"'
					}
				}
			),
			new webpack.optimize.UglifyJsPlugin(
				{
					sourceMap: true
				}
			)
		]
	}
);

module.exports = [prod, email];