// initialize the map on the "map" div with a given center and zoom

$(document).ready(function () {
  $.get("/api/get/list/place", function(places, status){
    drawMap(places)
  });

  function drawMap(places) {
    var defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
    var zoomLevel = 13;
    var mapIdContainer = 'map'
    var map = L.map(mapIdContainer, {attributionControl: false}).setView(defaultCoordHCM, zoomLevel);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    places.map(place => {
      var marker = L.marker([place.latitude_second, place.longtitude_second]).addTo(map);
      marker.bindPopup("<b>"+place.name+"</b><br>");
    })

    var searchControl = new L.esri.Controls.Geosearch().addTo(map);
    var results = new L.LayerGroup().addTo(map);

    searchControl.on('results', function(data){
      results.clearLayers();
      for (var i = data.results.length - 1; i >= 0; i--) {
        results.addLayer(L.marker(data.results[i].latlng));
      }
    });
  }
});