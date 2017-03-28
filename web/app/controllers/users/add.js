import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    addUser: function(){
      this.store.adapterFor('application').set('host', 'http://users.peoplerunning.co/api/v1/users');
      let newUser = this.get('store').createRecord('user', {
        userName: this.get('userName'),
        password: this.get('password'),
        firtsName: this.get('firtsName'),
        lastName: this.get('lastName'),
        email: this.get('email'),
        phoneNumber: this.get('phoneNumber')
      });
      newUser.save();
    }
  }
});
