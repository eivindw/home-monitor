requirejs.config({
   urlArgs: 'v=' + Math.random(),

   paths: {
      'jquery': '../lib/jquery-1.9.1',
      'underscore': '../lib/underscore-1.4.4',
      'hogan': '../lib/hogan-2.0.0',
      'hgn': '../lib/plugin/hgn',
      'text': '../lib/plugin/text',
      'highcharts': '../lib/Highcharts-2.3.5/js/highcharts',
      'highcharts-more': '../lib/Highcharts-2.3.5/js/highcharts-more',
      'bootstrap': '../lib/bootstrap/js/bootstrap',
      'moment': '../lib/moment-2.0.0',
      'moment-norwegian': '../lib/moment-nb',
      'signals': '../lib/signals',
      'crossroads': '../lib/crossroads.min',
      'hasher': '../lib/hasher.min'
   },

   shim: {
      'underscore': {
         exports: '_'
      },
      'bootstrap': ['jquery'],
      'highcharts': {
         exports: 'Highcharts'
      },
      'highcharts-more': {
         deps: ['highcharts'],
         exports: 'HighchartsMore'
      },
      'crossroads': ['signals'],
      'hasher': ['signals']
   }
});

define(function (require) {
   var $ = require('jquery');
   require('bootstrap');
   var crossroads = require('crossroads');
   var hasher = require('hasher');

   $(document).ready(function () {
      crossroads.addRoute('about', function() {
         console.log("About!");
      });

      function parseHash(newHash, oldHash){
         crossroads.parse(newHash);
      }

      hasher.initialized.add(parseHash); // parse initial hash
      hasher.changed.add(parseHash); //parse hash changes
      hasher.init();
   });
});
