import Ember from 'ember';

export default Ember.Controller.extend({



  actions: {
    addShirtStyle: function(){
      var reader = new FileReader();
      var imageBase64 = "";
      var self = this;

      reader.onloadend = function () {
        imageBase64 = reader.result.replace(/^data:image\/[a-z]+;base64,/, "");
        var imgpath = Ember.$('#image').val();
        var imgfile = imgpath.substr(imgpath.lastIndexOf('\\')+1);
        var imgext = imgfile.substr(imgfile.lastIndexOf('.')+1);
        self.store.adapterFor('application').set('host', 'http://catalog.soybackend.com');

        let newShirtStyle = self.get('store').createRecord('shirtstyle', {
          name: self.get('name'),
          imageExtension: imgext,
          image: imageBase64,
        });

        function transitionToIndex () {
          alert("Estilo creado");
          self.transitionToRoute('shirtstyles.list');
        }

        function failure (reason) {
          alert(reason);
        }

        newShirtStyle.save().then(transitionToIndex).catch(failure);
      };

      var file = Ember.$("#image")[0].files[0];
      if (file) {
        reader.readAsDataURL(file);
      }
    }
  }
});
