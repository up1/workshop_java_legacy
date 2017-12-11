module.exports = function (config) {
    config.set({
        basePath: '../../',
        exclude: [],
        frameworks: ['jasmine'],
        files: [
            'public/scripts/libs/angular-1.2.15.min.js',
            'public/scripts/libs/*.js',
            'public/scripts/**/*Module.js',
            'public/scripts/**/*.js',
            'public/scripts/**/*Spec.js',

            'public/**/*.html'
        ],
        ngHtml2JsPreprocessor: {
            moduleName: 'karma.cached.htmls',
            cacheIdFromPath: function (filepath) {
                return filepath.replace("public/", "/");
            }
        },
        preprocessors: {
            '**/*.html': ['ng-html2js'],
            'webapp/app/**/*js': ['coverage']
        },
        browsers: [
            'PhantomJS'
        ],
        autoWatch: false,
        singleRun: true,
        reporters: ['dots', 'junit'],
        junitReporter: {
            outputFile: '../build/test-results/test-results.xml'
        }
    });
};
