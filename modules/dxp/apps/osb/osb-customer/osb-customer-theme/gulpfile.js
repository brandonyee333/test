'use strict';

const gulp = require('gulp');
const liferayThemeTasks = require('liferay-theme-tasks');

const config = require('./webpack.config.js');
const log = require('fancy-log');
const webpack = require('webpack-stream');

liferayThemeTasks.registerTasks({
	gulp: gulp,
	hookFn: function(gulp) {
		gulp.hook('after:build:src', function(done) {
			gulp.src('./build/js/*.js')
			.pipe(webpack(config))
			.pipe(gulp.dest('./build/js/', {
					overwrite: true,
				})
			)
			.on('end', done);

			log('Starting \'after:build:src\'...');
		});
	}
});