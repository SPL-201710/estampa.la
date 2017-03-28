import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),

  actions: {
    authenticate: function() {
      var credentials = this.getProperties('identification', 'password');
      var authenticator = 'authenticator:jwt';

      this.get('session').authenticate(authenticator, credentials);
      // var settings = {
      //   "async": true,
      //   "crossDomain": true,
      //   "url": "http://users.peoplerunning.co/api/v1/users/login/",
      //   "method": "POST",
      //   "headers": {
      //     "content-type": "application/json",
      //     "cache-control": "no-cache",
      //     'Access-Control-Allow-Origin': 'http://users.peoplerunning.co/'
      //   },
      //   "processData": false,
      //   "data": {
      //     "username": "farruza",
      //     "password": "123"
      //   }
      // }
      //
      // Ember.$.ajax(settings).done(function (response) {
      //   console.log(response);
      // });

    }
  }
});
