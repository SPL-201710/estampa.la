import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
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
  ratePrint: "",
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
    showRatePrint(print){
      console.log(print);
      this.set('ratePrint', print);
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
    },
    showDetail(print){
      Ember.$("#print-detail-name").html(print.name);
      Ember.$("#print-detail-description").html(print.description);
      Ember.$("#print-detail-price").html(print.price);
      Ember.$("#print-detail-rating").html(print.rating);
      Ember.$("#print-detail-theme").html(print.theme.name);
      Ember.$("#print-detail-artist").html(print.ownerUsername);
      Ember.$("#print-detail-ratingCounts").html(print.ratingCounts);
      try{
          Ember.$("#detailModal").modal({backdrop: 'static'});
      }
      catch(err){
        $("#detailModal").modal({backdrop: 'static'});
      }
    },
    ratePrint(print){
      $("#currentRate").rateYo({
        ratedFill: "#3299ff",
        readOnly: true,
        starWidth: "40px"
      });
      var self = this;
      Ember.$.getJSON('http://catalog.soybackend.com/api/v1/prints/' + print.id, function(data){
        $("#currentRate").rateYo("rating", data.rating);
        $("#rateStars").rateYo("rating", 0);
        try{
            Ember.$("#rateModal").modal({backdrop: 'static'});
        }
        catch(err){
          $("#rateModal").modal({backdrop: 'static'});
        }
        self.set('ratePrint', print.id);
      });

    },
    saveRate(){
      var $rateYo = $("#rateStars").rateYo();
      var rating = $rateYo.rateYo("rating");

      var rate = {
        rate: rating,
        userId: this.get('session.data.authenticated.user.id')
      }

      var settings = {
        "url": "http://catalog.soybackend.com/api/v1/prints/" + this.get('ratePrint') + "/rate",
        "method": "POST",
        "headers": {
          "content-type": "application/json",
          "cache-control": "no-cache"
        },
        "data": JSON.stringify(rate)
      }

      Ember.$.ajax(settings).done(function (response) {
        Ember.$("#rateModal").modal('toggle');
        alert("Calificación Guardada");
      });
    },
    sharePrint(print){
      FB.ui({
        method: 'share',
        display: 'popup',
        href: 'http://web.soybackend.com/ver-estampa/' + print.id,
        hashtag: '#estampala',
        quote: 'Mira esta estampa en estampa.la'
      }, function(response){});
    }
  }
});
