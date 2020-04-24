const autoprefixer = require('autoprefixer');
const path = require('path');

const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
	entry: {
		email: path.resolve(__dirname, 'src/main/css/email.scss')
	},
	module: {
		rules: [
			{
				test: /\.scss$/,
				use: ExtractTextPlugin.extract(
					{
						fallback: 'style-loader',
						use: [
							{
								loader: 'css-loader',
								options: {
									importLoaders: 2
								}
							},
							{
								loader: 'postcss-loader',
								options: {
									plugins: () => [autoprefixer()]
								}
							},
							{
								loader: 'sass-loader'
							}
						]
					}
				)
			}
		]
	},
	output: {
		filename: '[name].css',
		path: path.resolve(__dirname, 'src/main/resources/META-INF/resources/dist')
	},
	plugins: [new ExtractTextPlugin('[name].css')]
};