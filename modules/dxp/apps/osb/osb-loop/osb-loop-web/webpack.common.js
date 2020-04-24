const autoprefixer = require('autoprefixer');
const fs = require('fs');
const path = require('path');
const webpack = require('webpack');

const BundleQueryStringPlugin = require('./bundle-query-string-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

const compass = path.resolve(
	__dirname,
	'node_modules/' +
	'compass-mixins/lib'
);

const mixins = path.resolve(
	__dirname,
	'node_modules/' +
	'liferay-frontend-common-css'
);

const bundleDirectory = 'src/main/resources/META-INF/resources/';

const PUBLIC_PATH = '/o/osb-loop-web/dist/';

function resolveModule(name) {
	return path.resolve(__dirname, `src/main/js/${name}`);
}

const config = {
	entry: {
		bundle: resolveModule('/init.js'),
		'ui-kit': resolveModule('/ui-kit')
	},
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.js$/,
				use: 'babel-loader'
			},
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
									plugins: () => [
										autoprefixer()
									]
								}
							},
							{
								loader: 'sass-loader',
								options: {
									includePaths: [compass, mixins]
								}
							}
						]
					}
				)
			},
			{
				test: /\.(eot|ttf|woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,
				use: 'file-loader'
			}
		]
	},
	output: {
		filename: '[name].nocsf.js',
		path: path.resolve(__dirname, bundleDirectory + 'dist'),
		publicPath: PUBLIC_PATH
	},
	plugins: [
		new ExtractTextPlugin('main.css'),
		new webpack.optimize.CommonsChunkPlugin(
			{
				name: 'bundle'
			}
		),
		new webpack.optimize.CommonsChunkPlugin(
			{
				name: 'vendors',
				minChunks: function(module) {
					return /node_modules/.test(module.resource);
				}
			}
		),
		new webpack.optimize.CommonsChunkPlugin(
			{
				name: "manifest",
				minChunks: Infinity
			}
		),
		new BundleQueryStringPlugin(),
		function() {
			this.plugin(
				'done',
				function(stats) {
					stats = stats.toJson();

					var hashObj = {};

					stats.chunks.forEach(
						function(chunk) {
							hashObj[chunk.files[0]] = chunk.hash;
						}
					);

					var sortedObj = {};

					Object.keys(hashObj)
						.sort()
						.forEach(
							function(key) {
								sortedObj[key] = hashObj[key];
							}
						);

					fs.writeFileSync(
						path.resolve(
							__dirname,
							bundleDirectory + 'dist/manifest.json'
						),
						JSON.stringify(sortedObj, null, '\t')
					);
				}
			);
		}
	]
};

module.exports = {
	bundleDirectory: bundleDirectory,
	config: config,
	publicPath: PUBLIC_PATH
};