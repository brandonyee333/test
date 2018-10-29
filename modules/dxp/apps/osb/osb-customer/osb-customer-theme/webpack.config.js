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
		library: 'theme',
		libraryTarget: 'window'
	}
};