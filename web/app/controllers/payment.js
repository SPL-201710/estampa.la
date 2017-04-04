import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    addPayment: function(){
      this.store.adapterFor('application').set('host', 'http://shopping-cart.peoplerunning.co');
      var today = new Date();
      var date_formatted = today.getFullYear() + "-" + (parseInt(today.getMonth())+1) + "-" + today.getDate();
      let newTransaction = this.get('store').createRecord('transaction', {
        transactionUser: this.get('session.data.authenticated.user.id'),
        shoppingCart: this.get('model.content')[0].id,
        date: date_formatted
      });

      var self = this;

      function transitionToIndex () {
        self.transitionToRoute('index');
      }

      function failure (reason) {
        alert(reason);
      }

      newTransaction.save().then(transitionToIndex).catch(failure);
    }
  }
});
