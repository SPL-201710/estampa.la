import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  actions: {
    modalPayment: function(){
      $("#modalpse").modal();
    },
    addPayment: function(){
      $("#modalpse").modal('toggle');
      $("#modalpayment").modal();
      (function myLoop (i) {
        var value = (11-i)*10;
         setTimeout(function () {
           $("#barra-pago").css("width", value + "%");
           $("#barra-pago").attr("aria-valuenow", value + "%");
           $("#barra-pago").html(value + "%");
           if (--i) myLoop(i);
           else $("#modalpayment").modal('toggle');
         }, 200)
      })(10);

      // var cartProductsg = [];
      // var products = JSON.parse(localStorage.getItem("products"));
      // var cart = JSON.parse(localStorage.getItem("cart"));
      // var self = this;
      //
      // products.forEach(function(data){
      //   var printsInShirts = [];
      //   data.printsInShirts.forEach(function(printin){
      //     printsInShirts.push({
      //       print: printin.print.id,
      //       shirtPrintPosition: printin.shirtPrintPosition
      //     });
      //   });
      //
      //   var textsInShirts = [];
      //   data.textsInShirts.forEach(function(textin){
      //     textsInShirts.push({
      //       printText: textin.printText,
      //       shirtPrintPosition: textin.shirtPrintPosition
      //     });
      //   });
      //
      //   var newProduct = {
      //     totalPrice: data.totalPrice,
      //     shirt: data.shirt.id,
      //     owner: data.owner,
      //     printsInShirts: printsInShirts,
      //     textsInShirts: textsInShirts
      //   };
      //
      //   var settings = {
      //     "async": false,
      //     "crossDomain": true,
      //     "url": "http://catalog.soybackend.com/api/v1/products",
      //     "method": "POST",
      //     "headers": {
      //       "content-type": "application/json",
      //       "cache-control": "no-cache"
      //     },
      //     "processData": false,
      //     "data": JSON.stringify(newProduct)
      //   };
      //   Ember.$.ajax(settings).done(function (response) {
      //     cart.cartProducts.forEach(function(prod){
      //         if(data.id===prod.product){
      //           cartProductsg.push({
      //             quantity: prod.quantity,
      //             subtotal: prod.subtotal,
      //             product: response.id
      //           });
      //         }
      //       });
      //   });
      // });
      //
      // var newCart = {
      //     "subtotal" : cart.subtotal,
      //     "shippingValue" : cart.shippingValue,
      //     "total" : cart.total,
      //     "date" : "2017-04-18",
      //     "owner" : this.get('session.data.authenticated.user.id'),
      //     "payment" : this.get('session.data.authenticated.user.id'),
      //     "cartProducts": cartProductsg
      // };
      //
      // var settings = {
      //   "async": false,
      //   "crossDomain": true,
      //   "url": "http://shopping-cart.soybackend.com/api/v1/carts",
      //   "method": "POST",
      //   "headers": {
      //     "content-type": "application/json",
      //     "cache-control": "no-cache"
      //   },
      //   "processData": false,
      //   "data": JSON.stringify(newCart)
      // };
      //
      // Ember.$.ajax(settings).done(function (response) {
      //   var newpayment = {
      //     "date" : "2017-04-18",
      //     "owner" : self.get('session.data.authenticated.user.id'),
      //     "shoppingcart": response.id,
      //     "total": response.total,
      //     "pse_method": {
      //       "firtsName": "Jose",
      //     	"lastName": "Alvarez",
      //     	"phone": "5585",
      //     	"email": "jose@estampa.la",
      //     	"bank": "Bancolombia",
      //     	"bankCode": "BC",
      //     	"reference": "6549874659",
      //     	"identificationType": "CC",
      //     	"identification": "987798"
      //     }
      //   };
      //   var settings = {
      //     "async": false,
      //     "crossDomain": true,
      //     "url": "http://payment.soybackend.com/api/v1/payments",
      //     "method": "POST",
      //     "headers": {
      //       "content-type": "application/json",
      //       "cache-control": "no-cache"
      //     },
      //     "processData": false,
      //     "data": JSON.stringify(newpayment)
      //   };
      //   Ember.$.ajax(settings).done(function (response) {
      //     console.log(response);
      //     alert("Compra realizada");
      //     self.transitionToRoute('index');
      //   });
      //
      // });
    }
  }
});
