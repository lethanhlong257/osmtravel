// initialize the map on the "map" div with a given center and zoom

$(document).ready(function () {
  var host = "localhost:8080";

  var parameter = "?keyword='Nguyen dinh chieu'";
  var places = [];
  var currenntURL = window.location.href;

  if (currenntURL.indexOf("/search") > -1) {
    var searchEndPoint = "/api/v1.0/search";
    var searchParams = getUrlParameter("keyword");
    var searchAPI = host + searchEndPoint + "?keyword=" + encodeURI(searchParams);
    $.get(searchAPI, function (searchResult, status) {
      let searchList = searchResult.searchList;
      
    })
  } else {
    drawMap(places);
  }

});

function getUrlParameter(sParam) {
  var sPageURL = window.location.search.substring(1),
      sURLVariables = sPageURL.split('&'),
      sParameterName,
      i;

  for (i = 0; i < sURLVariables.length; i++) {
    sParameterName = sURLVariables[i].split('=');

    if (sParameterName[0] === sParam) {
      return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
    }
  }
}

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
}