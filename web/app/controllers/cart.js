import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    this.set('subtotal', 0);
    this.set('total', 0);
    this.set('cartProducts', []);

    this.send("calculate");
  },
  subtotal: 0,
  shippingValue: 5000,
  total: 0,
  cartProducts: [],
  actions: {
    calculate: function(){
      var products = JSON.parse(localStorage.getItem("products"));
      var products_array = [];
      var subtotal_calculate = 0;

      products.forEach(function (data) {
        var product_quantity = Ember.$("#product-" + data.id + " option:selected").val();
        if(product_quantity == undefined)
          product_quantity = 1;
        var newProduct = {
          quantity: parseInt(product_quantity),
          subtotal: data.totalPrice * parseInt(product_quantity),
          product: data.id
        };
        subtotal_calculate = subtotal_calculate + newProduct.subtotal;
        products_array.push(newProduct);
      });
      this.set('subtotal', subtotal_calculate);
      this.set('total', subtotal_calculate + parseInt(this.get('shippingValue')));
      this.set('cartProducts', products_array);
    },
    addCart: function(){
      var cart = {
        subtotal: this.get('subtotal'),
        shippingValue: this.get('shippingValue'),
        total: this.get('total'),
        cartProducts: this.get('cartProducts')
      };

      localStorage.setItem("cart", JSON.stringify(cart));
      this.transitionToRoute('payment');
    },
    deleteProduct: function(product){
      var products = JSON.parse(localStorage.getItem("products"));
      products = products.filter(function(item) {
        if(item.id!==product){
          return item;
        }
      });
      localStorage.setItem("products", JSON.stringify(products));
      Ember.$('#'+product).prev().remove();
      Ember.$('#'+product).remove();

      this.send("calculate");
    }
  }
});
