const clayCss = require('clay-css');
const path = require('path');

const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const PUBLIC_PATH = '/o/osb-customer-account-entry-details-web/dist/';

module.exports = {
	entry: './src/main/js/main.js',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				loader: 'babel-loader'
			},
			{
				test: /\.scss$/,
				use: [
					MiniCssExtractPlugin.loader,
					{
						loader: 'css-loader',
						options: {
							importLoaders: 2,
							modules: false
						}
					},
					{
						loader: 'postcss-loader',
						options: {
							plugins: () => [require('autoprefixer')()]
						}
					},
					{
						loader: 'sass-loader',
						options: {
							includePaths: [clayCss.includePaths]
						}
					}
				]
			}
		]
	},
	output: {
		filename: 'main.js',
		library: 'HelpCenter',
		libraryTarget: 'window',
		path: path.resolve('src/main/resources/META-INF/resources/dist'),
		publicPath: PUBLIC_PATH
	},
	plugins: [
		new MiniCssExtractPlugin(
			{
				filename: 'main.css'
			}
		)
	]
};