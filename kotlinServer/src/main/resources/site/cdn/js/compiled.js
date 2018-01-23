if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'compiled'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'compiled'.");
}
var compiled = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function main(args) {
    println('JavaScript start');
  }
  var package$cc = _.cc || (_.cc = {});
  var package$jorgen = package$cc.jorgen || (package$cc.jorgen = {});
  var package$script = package$jorgen.script || (package$jorgen.script = {});
  package$script.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('compiled', _);
  return _;
}(typeof compiled === 'undefined' ? {} : compiled, kotlin);
