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

      var user = Ember.$("#user option:selected").val();
      if(user != '') {
          Ember.$.getJSON('http://localhost:8082/api/v1/reports/salesbyartist/' + user, function(result) {

            var dataset = [];
            result.forEach(function(data) {
              var row = [];
              row.push(data.printName);
              row.push("<a href='" + data.printImage + "'>Clic para consultar</a>");
              row.push(data.quantitySold);
              row.push(data.totalSold);
              dataset.push(row);
            });

            Ember.$('#data').DataTable( {
                data: dataset,
                columns: [
                  { title: "Nombre" },
                  { title: "Imagen" },
                  { title: "Cantidad" },
                  { title: "Total" }
                ]
            });
            self.set('initTable', true);
          });
      }
    }
  }
});
