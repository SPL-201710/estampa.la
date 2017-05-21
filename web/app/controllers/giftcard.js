import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    modalPayment: function(){
      try{
          Ember.$("#modalpse").modal({backdrop: 'static'});
      }
      catch(err){
        $("#modalpse").modal({backdrop: 'static'});
      }
    },
    addGiftCard: function(){
        this.store.adapterFor('application').set('host', 'http://payment.soybackend.com');
        var self = this;
        var d = new Date();
        var datestring = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

        let newGiftcard = this.get('store').createRecord('giftcard', {
          buyer: self.get('session.data.authenticated.user.id'),
          receiver: Ember.$("#receiver option:selected").val(),
          initialBalance: Ember.$("#price").val(),
          pse_method: {
            firstName: Ember.$("#pse_firstname").val(),
            lastName: Ember.$("#pse_lastname").val(),
            phone: Ember.$("#pse_phone").val(),
            email: Ember.$("#pse_email").val(),
            bank: Ember.$("#bank option:selected").val(),
            bankCode: "BC",
            reference: "6549874659",
            identificationType: Ember.$("#identification_type option:selected").val(),
            identification: Ember.$("#pse_identifition").val(),
            date: datestring
          }
        });

        function transitionToIndex () {
          Ember.$("#modalpse").modal('toggle');
          Ember.$("#modalpayment").modal({backdrop: 'static'});
          (function myLoop (i) {
            var value = (11-i)*10;
             setTimeout(function () {
               Ember.$("#barra-pago").css("width", value + "%");
               Ember.$("#barra-pago").attr("aria-valuenow", value + "%");
               Ember.$("#barra-pago").html(value + "%");
               if (--i) myLoop(i);
               else{
                 Ember.$("#modalpayment").modal('toggle');
                 alert("Compra realizada");
                 self.transitionToRoute('index');
               }
             }, 200)
          })(10);
        }

        function failure (reason) {
          alert(reason);
        }
        newGiftcard.save().then(transitionToIndex).catch(failure);
    }
  }
});
