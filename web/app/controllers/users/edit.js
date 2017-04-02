import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    editUser: function(){

      //Asi deberia funcionar, pero esta enviando un patch

      // this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co');
      // this.get('store').findRecord('user', this.get('session.data.authenticated.user.id')).then(function(user) {
      //   post.set('firstName', Ember.$("#firstName").val());
      //   post.set('lastName', Ember.$("#lastName").val());
      //   post.set('email', Ember.$("#email").val());
      //   post.set('phoneNumber', Ember.$("#phoneNumber").val());
      //   post.save(); // => PATCH to '/users/id'
      // });

      var settings = {
        "url": "http://users.peoplerunning.co/api/v1/users/" + this.get('session.data.authenticated.user.id'),
        "method": "PUT",
        "headers": {
          "content-type": "application/json"
        },
        "processData": false,
        "data": JSON.stringify({
          "firstName": Ember.$("#firstName").val(),
          "lastName": Ember.$("#lastName").val(),
          "email": Ember.$("#email").val(),
          "phoneNumber": Ember.$("#phoneNumber").val()
        })
      };
      var self = this;
      $.ajax(settings).done(function (response) {
        alert("Datos Actualizados");
        self.transitionToRoute('index');
      });

    }
  }
});
