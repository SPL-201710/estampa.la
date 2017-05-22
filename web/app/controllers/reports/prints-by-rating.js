import Ember from 'ember';

export default Ember.Controller.extend({
  initTable: false,
  session: Ember.inject.service('session'),
  actions: {
    loadData: function(){
      var self = this;
      if(this.get('initTable')) {
        var table = Ember.$('#data').DataTable();
        table.clear().draw();
        table.destroy();
      }

      var rating_min = Ember.$('#rating_min').val();
      var rating_max = Ember.$('#rating_max').val();

      if(rating_min != '' && rating_max !='') {
        var url = 'http://localhost:8082/api/v1/reports/printsbyrating/';
        var params = '?rating_min=' + rating_min + '&rating_max=' + rating_max;
        url = url + params;

        Ember.$.getJSON(url, function(result) {
          var dataset = [];
          result.forEach(function(data) {
            var row = [];
            row.push(data.name);
            row.push("<a href='" + data.image + "'>Clic para consultar</a>");
            row.push(data.ownerUsername);
            row.push(data.ownerEmail);
            row.push(data.price);
            row.push(data.rating);
            row.push(data.ratingCounts);
            row.push(data.theme);
            dataset.push(row);
          });

          Ember.$('#data').DataTable( {
              data: dataset,
              columns: [
                { title: "Nombre" },
                { title: "Imagen" },
                { title: "Propietario" },
                { title: "Correo" },
                { title: "Precio" },
                { title: "Rating" },
                { title: "Cantidad" },
                { title: "Tema" }
              ]
          });
          self.set('initTable', true);
        });
      }
      else {
        alert('Debe ingresar información en los filtros del reporte.');
      }
    }
  }
});
