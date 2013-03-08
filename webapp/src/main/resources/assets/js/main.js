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
      'routie': '../lib/routie.min',
      'easel': '../lib/easeljs-0.6.0.min'
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
      }
   }
});

define(function(require) {
   var $ = require('jquery');
   require('bootstrap');
   require('routie');

   $(document).ready(function() {
      routie({
         '': function() {
            console.log("Home!");
         },
         'about': function() {
            console.log("About!");
         },
         'contact': function() {
            console.log("Contact!")
         }
      });
   });
});
