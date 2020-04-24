var gulp = require('gulp');
var svgSprite = require('gulp-svg-sprite');

gulp.task(
	'icons',
	function() {
		gulp.src('src/main/resources/META-INF/resources/images/icons/icon-*/**/*.svg')
			.pipe(
				svgSprite(
					{
						mode: {
							inline: false,
							symbol: {
								dest: '',
								inline: true,
								sprite: 'loop_icons.svg'
							}
						},
						shape: {
							id: {
								generator: function(str) {
									var name = str.replace(/icon-.+\//, '').replace('.svg', '');
									var size = str.replace('icon-', '').replace(/\/.*.svg/, '');

									return 'loop-icon-' + name + '-' + size;
								}
							}
						}
					}
				)
			).pipe(
				gulp.dest('src/main/resources/META-INF/resources/images/icons/dist')
			);
	}
);