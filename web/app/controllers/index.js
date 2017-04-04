import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    this.set('author', "");
  },
  author: "",
  filteredPrints: Ember.computed('author', function() {
    console.log(this.get('author'));
      // const filterTerm = this.get('filter');
      // var model = this.get('model');
      //
      // var filtered = model.filter( function(book) {
      //     return book.get('title').indexOf(filterTerm) !== -1;
      // });
      //
      // return filtered;
  }),
  actions: {
    changeAuthor(param) {
      this.set('author', param);
    }
  }
});
