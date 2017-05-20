import Ember from 'ember';

export function feature(params) {
  var active = false;
  var services = JSON.parse(localStorage.getItem("features"));
  services.forEach(function(service){
    service.features.forEach(function (feature) {
      if(feature.name === params[0] && feature.active===true){
        active = true;
      }
    });
  });
  return active;
}

export default Ember.Helper.helper(feature);
