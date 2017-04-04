import Ember from 'ember';

export default Ember.Controller.extend({
  init: function() {
    this._super();
    this.set('author', "");
    this.set('theme', "");
    this.set('rate', "");
  },
  owner: "",
  theme: "",
  rate: "",

  filteredPrints: Ember.computed('owner', 'theme', 'rate', function() {
    console.log(this.get('owner'));
    console.log(this.get('theme'));
    console.log(this.get('rate'));
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
    changeOwner(param) {
      this.set('owner', param);
    },
    changeTheme(param) {
      this.set('theme', param);
    },
    changeRate(param) {
      this.set('rate', param);
    }
  }
});
