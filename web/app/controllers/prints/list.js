import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    changeTheme: function(print){
      console.log(print);
    }
  }
});
