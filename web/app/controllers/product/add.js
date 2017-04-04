import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    //set total price
    var printsSelected = JSON.parse(localStorage.getItem("printsSelected"));
    var totalPrice = 0
    var printsInShirts = [];
    printsSelected.forEach(function(data){
      totalPrice += data.price;
      printsInShirts.push({
        print: data.id,
        shirtPrintPosition: ""
      });
    });
    this.set('printsSelected', printsSelected);
    this.set('totalPrice', totalPrice);
    this.set('printsInShirts', printsInShirts);
    this.set('shirt', "");
    this.set('textsInShirts', []);
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
      printsInShirts.forEach(function(data){
        if(data.print==printid){
          data.shirtPrintPosition=Ember.$("#select-" + printid + " option:selected").val();
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
      this.set("shirt", shirtid);
    },
    addText: function(){
      var textsInShirts = this.get("textsInShirts");
      var textInShirt = {
        shirtPrintPosition: this.get('textPosition'),
        printText: {
          font: this.get("printFont"),
          message: this.get("message"),
          size: this.get("size")
        }
      }
      textsInShirts.push(textInShirt);
      this.set('textsInShirts', textsInShirts);
      this.set('textadded', Math.random());
    },
    deleteText: function(textmessage){
      var textsInShirts = this.get('textsInShirts');
      textsInShirts = textsInShirts.filter(function(item) {
        if(item.printText.message!==textmessage) return item;
      });
      this.set('textsInShirts', textsInShirts);
      this.set('textadded', Math.random());
    },
    addProduct: function(){
      this.store.adapterFor('application').set('host', 'http://catalog.peoplerunning.co');
      let newProduct = this.get('store').createRecord('product', {
        totalPrice: this.get('totalPrice'),
        shirt: this.get('shirt'),
      	printsInShirts: this.get('printsInShirts'),
      	textsInShirts: this.get('textsInShirts')
      });

      var self = this;

      function transitionToIndex () {
        self.transitionToRoute('index');
      }

      function failure (reason) {
        alert(reason);
      }

      newProduct.save().then(transitionToIndex).catch(failure);
    }
  }
});
