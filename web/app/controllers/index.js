import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    this.set('author', "");
    this.set('theme', "");
    this.set('rating', "");
    this.set('result', []);
    localStorage.setItem("printsSelected", null);
    localStorage.setItem("cart", null);
  },
  owner: "",
  theme: "",
  rating: "",
  result: [],

  filteredPrints: Ember.computed('owner', 'theme', 'rating', function() {
    this.set('result', []);
    var self = this;
    Ember.$.getJSON(
      'http://catalog.soybackend.com/api/v1/prints/',
      {
        owner: this.get('owner'),
        theme: this.get('theme'),
        rating: this.get('rating')
      }, function(data){
        data.content.forEach(function(print){
          self.result.addObject(print);
       });
      });
  }),
  actions: {
    changeOwner(owner) {
      this.set('owner', owner);
    },
    changeTheme(theme) {
      this.set('theme', theme);
    },
    changeRating(rating) {
      this.set('rating', rating);
    },
    selectPrint(print) {
      var printsSelected = JSON.parse(localStorage.getItem("printsSelected"));

      if(printsSelected === null){
        printsSelected = [];
        printsSelected.push(print);
        Ember.$("#print-"+print.id).addClass("product-item-selected");
      }
      else{
        var exist = false;
        printsSelected.forEach(function(data){
          if(data.id===print.id){
            exist=true;
          }
        });

        if(exist===true){
          printsSelected = printsSelected.filter(function(item) {
            if(item.id!==print.id){
              return item;
            }
          });
          Ember.$("#print-"+print.id).removeClass("product-item-selected");
        }
        else{
          printsSelected.push(print);
          Ember.$("#print-"+print.id).addClass("product-item-selected");
        }
      }
      localStorage.setItem("printsSelected", JSON.stringify(printsSelected));
    }    
  }
});
