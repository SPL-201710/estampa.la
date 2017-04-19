import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  init: function() {
    this._super();
    var printsSelected = JSON.parse(localStorage.getItem("printsSelected"));
    var totalPrice = 0;
    var printsInShirts = [];
    printsSelected.forEach(function(data){
      totalPrice += data.price;
      printsInShirts.push({
        print: data,
        shirtPrintPosition: ""
      });
    });
    this.set('printsSelected', printsSelected);
    this.set('totalPrice', totalPrice);
    this.set('printsInShirts', printsInShirts);
    this.set('shirt', "");
    this.set('textsInShirts', []);
    this.set('currentShirt', []);
    this.set('texts', []);
    this.set('textadded', "");
    this.set('textPosition', "");
    this.set('printFont', "");
    // debugger;
  },
  printsSelected: [],
  totalPrice: 0,
  printsInShirts: [],
  shirt: "",
  textsInShirts: [],
  textPosition: "",
  printFont: "",
  textadded: "",
  texts: [],
  currentShirt: [],
  textsInShirtsEvent: Ember.computed('textadded',  function() {
    this.set('texts', []);
    var textsInShirts = this.get("textsInShirts");
    var self = this;
    textsInShirts.forEach(function(text){
      self.texts.addObject(text);
    });
  }),
  actions: {
    selectPrintPosition: function(printid){
      var printsInShirts = this.get('printsInShirts');      
      var position_selected = Ember.$("#select-" + printid + " option:selected").val();
      printsInShirts.forEach(function(data){
        if(data.print.id===printid){
          data.shirtPrintPosition=position_selected;
        }
      });

      var local_prints = JSON.parse(localStorage.getItem("printsSelected"));
      local_prints.forEach(function(data){
        if(data.id===printid){
          Ember.$('#printed-'+data.id).attr('src','data:image/png;base64, ' + data.image);
          Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/shirtprintpositions/' + position_selected).then(function(position){
            Ember.$('#printed-'+data.id).css('top', position.hightInCentimeters + '%');
          });
        }
      });
    },
    selectTextPosition: function(positionid){
      this.set("textPosition", positionid);
    },
    selectPrintFont: function(fontid){
      this.set("printFont", fontid);
    },
    selectShirt: function(shirtid){
      var self = this;
      Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/shirts/'+shirtid).then(function(data) {
        self.set("shirt", data);
        self.set("currentShirt", data.color);
        Ember.$('#current_shirt_image').attr('src','data:image/png;base64, ' + data.color.image);
      });
    },
    addText: function(){
      var textid = Date.now().toString();
      var textsInShirts = this.get("textsInShirts");
      var textInShirt = {
        id: textid,
        shirtPrintPosition: this.get('textPosition'),
        printText: {
          font: this.get("printFont"),
          message: this.get("message"),
          size: this.get("size")
        }
      };
      textsInShirts.push(textInShirt);
      this.set('textsInShirts', textsInShirts);
      this.set('textadded', Math.random());
      var self = this;
      Ember.$.getJSON('http://catalog.peoplerunning.co/api/v1/shirtprintpositions/' + this.get('textPosition')).then(function(data) {
        Ember.$('#shirt_area').append('<p id=' + textid + ' style="width: 100%; text-align: center; position: absolute;">' + self.get("message") + ' </p>');
        Ember.$('#'+textid).css('top', data.hightInCentimeters + '%');
      });
    },
    deleteText: function(idtext){
      var textsInShirts = this.get('textsInShirts');
      textsInShirts = textsInShirts.filter(function(item) {
        if(item.id!==idtext){
          return item;
        }
      });
      this.set('textsInShirts', textsInShirts);
      this.set('textadded', Math.random());
      Ember.$('#'+idtext).remove();
    },
    addProduct: function(){
      var product = {
        id: Date.now().toString(),
        totalPrice: this.get('totalPrice'),
        shirt: this.get('shirt'),
        owner: this.get('session.data.authenticated.user.id'),
      	printsInShirts: this.get('printsInShirts'),
      	textsInShirts: this.get('textsInShirts')
      };
      var products = localStorage.getItem("products");
      if(products==="null"){
        products = [];
        products.push(product);
      }
      else{
        products.push(product);
      }
      localStorage.setItem("products", JSON.stringify(products));
      this.transitionToRoute('cart');
    }
  }
});