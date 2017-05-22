import Ember from 'ember';

export default Ember.Controller.extend({
  cart: [],
  products: [],
  getProducts: Ember.computed('cart', function() {
    var self = this;
    var cart = this.get('cart');
    try{
      cart.cartProducts.forEach(function(products){
        Ember.$.getJSON('http://catalog.soybackend.com/api/v1/products/' + products.product, function(product){
          self.products.addObject(product);
        });
      });
    }
    catch(err){
      console.log(err);
    }
  }),
  getcart: Ember.computed('model', function() {
    var payment = this.get('model.payment');
    var self = this;
    Ember.$.getJSON('http://shopping-cart.soybackend.com/api/v1/carts/' + payment.shoppingcart, function(cart){
      self.set('cart', cart);
    });
  }),
  actions: {
    shareProduct(product){
      FB.ui({
        method: 'share',
        display: 'popup',
        href: 'http://web.soybackend.com/ver-producto/' + product,
        hashtag: '#estampala',
        quote: 'Mira este producto en estampa.la'
      }, function(response){});
    }
  }
});
