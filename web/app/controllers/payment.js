import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    modalPayment: function(){
      var methodSelected = Ember.$('input[name=payment]:checked').val();
      if(methodSelected=='pse'){
        try{
            Ember.$("#modalpse").modal({backdrop: 'static'});
        }
        catch(err){
          $("#modalpse").modal({backdrop: 'static'});
        }
      }
      if(methodSelected=='credito'){
        try{
            Ember.$("#modalcredito").modal({backdrop: 'static'});
        }
        catch(err){
          $("#modalcredito").modal({backdrop: 'static'});
        }
      }
      if(methodSelected=='regalo'){
        try{
            Ember.$("#modalregalo").modal({backdrop: 'static'});
        }
        catch(err){
          $("#modalregalo").modal({backdrop: 'static'});
        }
      }
    },
    validateGiftcard: function(total){
      var giftcard_balance = Ember.$("#gift_card option:selected").html();

      if(giftcard_balance >= total) {
        Ember.$("#btnPay").attr('disabled', false);
        Ember.$("#msg").attr('hidden', true);
      }
      else {
        Ember.$("#btnPay").attr('disabled', true);
        Ember.$("#msg").attr('hidden', false);
      }
    },
    addPayment: function() {
      var cartProductsg = [];
      var products = JSON.parse(localStorage.getItem("products"));
      var cart = JSON.parse(localStorage.getItem("cart"));
      var self = this;

      products.forEach(function(data){
        var printsInShirts = [];
        data.printsInShirts.forEach(function(printin){
          printsInShirts.push({
            print: printin.print.id,
            shirtPrintPosition: printin.shirtPrintPosition
          });
        });

        var textsInShirts = [];
        data.textsInShirts.forEach(function(textin){
          textsInShirts.push({
            printText: textin.printText,
            shirtPrintPosition: textin.shirtPrintPosition
          });
        });

        var newProduct = {
          totalPrice: data.totalPrice,
          shirt: data.shirt.id,
          owner: data.owner,
          printsInShirts: printsInShirts,
          textsInShirts: textsInShirts
        };

        var settings = {
          "async": false,
          "crossDomain": true,
          "url": "http://catalog.soybackend.com/api/v1/products",
          "method": "POST",
          "headers": {
            "content-type": "application/json",
            "cache-control": "no-cache"
          },
          "processData": false,
          "data": JSON.stringify(newProduct)
        };
        Ember.$.ajax(settings).done(function (response) {
          cart.cartProducts.forEach(function(prod){
              if(data.id===prod.product){
                cartProductsg.push({
                  quantity: prod.quantity,
                  subtotal: prod.subtotal,
                  product: response.id
                });
              }
            });
        });
      });

      var newCart = {
          "subtotal" : cart.subtotal,
          "shippingValue" : cart.shippingValue,
          "total" : cart.total,
          "date" : "2017-04-18",
          "owner" : this.get('session.data.authenticated.user.id'),
          "cartProducts": cartProductsg
      };

      var settings = {
        "async": false,
        "crossDomain": true,
        "url": "http://shopping-cart.soybackend.com/api/v1/carts",
        "method": "POST",
        "headers": {
          "content-type": "application/json",
          "cache-control": "no-cache"
        },
        "processData": false,
        "data": JSON.stringify(newCart)
      };

      Ember.$.ajax(settings).done(function (response) {
        var methodSelected = Ember.$('input[name=payment]:checked').val();
        var d = new Date();
        var datestring = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

        if(methodSelected=='pse'){
          var newpayment = {
            "date" : datestring,
            "owner" : self.get('session.data.authenticated.user.id'),
            "shoppingcart": response.id,
            "total": response.total,
            "pse_method": {
              "firtsName": Ember.$("#pse_firstname").val(),
            	"lastName": Ember.$("#pse_lastname").val(),
            	"phone": Ember.$("#pse_phone").val(),
            	"email": Ember.$("#pse_email").val(),
            	"bank": Ember.$("#bank option:selected").val(),
            	"bankCode": "BC",
            	"reference": "6549874659",
            	"identificationType": Ember.$("#identification_type option:selected").val(),
            	"identification": Ember.$("#pse_identifition").val()
            }
          };
        };

        if(methodSelected=='credito'){
          var newpayment = {
            "date" : datestring,
            "owner" : self.get('session.data.authenticated.user.id'),
            "shoppingcart": response.id,
            "total": response.total,
            "credit_method": {
              "firtsName": Ember.$("#credit_firstname").val(),
            	"lastName": Ember.$("#credit_lastname").val(),
            	"email": Ember.$("#credit_email").val(),
            	"bank": Ember.$("#credit_bank option:selected").val(),
            	"identificationType": Ember.$("#credit_identification_type option:selected").val(),
            	"identification": Ember.$("#credit_identification").val(),
              "type": Ember.$("#credit_card_type option:selected").val(),
              "number": Ember.$("#credit_number").val(),
              "cvc": Ember.$("#credit_cvc").val()
            }
          };
        }

        if(methodSelected=='regalo'){
          var newpayment = {
            "date" : datestring,
            "owner" : self.get('session.data.authenticated.user.id'),
            "shoppingcart": response.id,
            "total": response.total,
            "giftcard": Ember.$("#gift_card option:selected").val()
          };
        }

        var settings = {
          "async": false,
          "crossDomain": true,
          "url": "http://payment.soybackend.com/api/v1/payments",
          "method": "POST",
          "headers": {
            "content-type": "application/json",
            "cache-control": "no-cache"
          },
          "processData": false,
          "data": JSON.stringify(newpayment)
        };
        Ember.$.ajax(settings).done(function (response) {
          console.log(response);
          var methodSelected = Ember.$('input[name=payment]:checked').val();
          if(methodSelected=='pse'){
            Ember.$("#modalpse").modal('toggle');
          }
          if(methodSelected=='credito'){
            Ember.$("#modalcredito").modal('toggle');
          }
          if(methodSelected=='regalo'){
            Ember.$("#modalregalo").modal('toggle');
          }
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
                 // Limpiar localStorage
                 localStorage.setItem("products", '[]');
                 alert("Compra realizada");
                 self.transitionToRoute('summary', response.id);
               }
             }, 200)
          })(10);
        });

      });

    }
  }
});
