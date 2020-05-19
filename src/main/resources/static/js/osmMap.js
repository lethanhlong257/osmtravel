// initialize the map on the "map" div with a given center and zoom

$(document).ready(function () {
  var host = "localhost:8080"
  var places = [];
  drawMap(places);

  function drawMap(places) {
    var defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
    var zoomLevel = 13;
    var mapIdContainer = 'map'
    var map = L.map(mapIdContainer, {attributionControl: false}).setView(defaultCoordHCM, zoomLevel);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    places.map(place => {
      var marker = L.marker([place.lat, place.lon]).addTo(map);
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
  
  $("#btn-search-point").onclick(function (e) {
    alert("abc")
    var keyword = $('#search-map-key-word').val();
    if (keyword === "") return false;
    var endPoint = "/search";
    var parameter = "?name=" + keyword;
    var api = host + endPoint + parameter ;
    $.get(api , function(data, status){
      var searchList = data.SearchList;
      searchList.forEach(point => {
        places.push({
          name: point.name,
          lat: point.coordinates[0],
          lon: point.coordinates[1]
        })
      });

      drawMap(places);
    });

    return false;
  })

});