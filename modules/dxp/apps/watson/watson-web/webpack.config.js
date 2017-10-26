var webpack = require('webpack');

var jsDirectory = `${__dirname}/src/main/resources/META-INF/resources/js`;

var plugins = [
	new webpack.DefinePlugin(
		{
			'process.env': {
				NODE_ENV: `"${process.env.NODE_ENV || 'development'}"`
			}
		}
	)
];

if (process.env.NODE_ENV === 'production') {
	plugins.push(
		new webpack.optimize.OccurrenceOrderPlugin(),
		new webpack.optimize.UglifyJsPlugin(
			{
				compress: {
					drop_console: true,
					warnings: false
				}
			}
		)
	);
}

module.exports = {
	context: `${jsDirectory}/src`,
	entry: `${jsDirectory}/src/main.js`,
	module: {
		loaders: [
			{
				exclude: /node_modules/,
				loader: 'babel-loader',
				query: {
					babelrc: false,
					plugins: ['lodash'],
					presets: ['es2015', 'metal-jsx', 'stage-2']
				},
				test: /\.js$/
			}
		]
	},
	output: {
		filename: 'bundle.nocsf.js',
		path: `${jsDirectory}/dist`
	},
	plugins,
	resolve: {
		alias: {
			cropperjs: 'cropperjs/dist/cropper.min',
			quill: 'quill/dist/quill.min',
			vis: 'vis/dist/vis.min'
		}
	}
};