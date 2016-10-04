// This is a shoehorn for the headroom.js to work, until I bring in Cljs

document.addEventListener("DOMContentLoaded", function() {
  // grab header element
  var myElement = document.querySelector("header");
  // construct an instance of Headroom, passing the element
  var headroom = new Headroom(myElement);
  // initialise
  headroom.init();
});
