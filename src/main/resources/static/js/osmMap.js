// initialize the map on the "map" div with a given center and zoom
const host = "http://www.localhost:8080";
const SUCCESS_STATUS = "success"
const OK_STATUS = "OK"
let CURRENT_LOCATION = 'current+location';
let places = [];
let currenntURL = window.location.href;
const hostingGeoCodingAPI = "https://maps.googleapis.com/maps/api/geocode/json"
let isRouting = false

// Account lethanhlong257 - need to change before submit code and defend
// Becareful to use because it is limited for request
const GOOGLE_MAP_API_KEY = "AIzaSyAi0Yb8-l2pZVk2MOo8U2p26q2y9PcaS4k.r3m0v3";

let defaultCoordHCM = [10.7743, 106.6669]; // coord mặc định, chinh giữa HCMC
let zoomLevel = 13;
let mapIdContainer = 'map'
let map = L.map(mapIdContainer, {attributionControl: false})
let marker = null;

//let currentLocation = {lat: 10.7743, lon: 106.6669, name: 'my current location', address:'' } // Hoặc đổi qua toạ độ khác
let currentLocation = null;

$(document).ready(function () {
  $('.find-on-map-button').click( function(){
    scrollToMap()
    handleFindPointOnMap($(this).attr('data-selectable'))
  })

  $('#current-location-button').click(function () {
    handleCurrentLocation()
  })
  if (currenntURL.indexOf("/search?") > -1) {
    var searchEndPoint = "/api/v1.0/search";
    var searchParams = getUrlParameter("keyword");
    var searchAPI = host + searchEndPoint + "?keyword=" + encodeURI(searchParams);
    $.get(searchAPI, function (searchResult, status) {
      if (status === SUCCESS_STATUS) {
        let searchList = searchResult.SearchList;
        for (let i = 0; i < searchList.length; i ++) {
          let result = searchList[i];
          let point = {
            name: result.name,
            lon: result.coordinates[0],
            lat: result.coordinates[1]
          }
          places.push(point)
        }
        drawMap(places, []);
      } else {
        drawMap(places, []);
      }
    })
  }
  else if (currenntURL.indexOf("/routing") > -1) {
    let from = decodeURI(getUrlParameter("from"))
    let to = decodeURI(getUrlParameter("to"))
    if (from === "" || to === "") {
      alert("From and To cannot be empty")
      return
    }
    routingNodes(from, to)
  }
  else if (currenntURL.indexOf("/search/advanced") > -1) {
    let keyword = decodeURI(getUrlParameter("keyword"))
    keyword = keyword.split("+").join(" ")
    if (keyword === "") {
      alert("Keyword cannot be empty")
      return
    }
    searchByGoogleGeocoding(keyword, function (result) {
      places = result
      console.log(places)
      drawMap(places, [])
    })
  }
  else {
    drawMap(places, []);
  }

  $("#button-advanced-search").click(function (event) {
    const advancedSearchKeyword = $("#input-advanced-search").val()
    searchByGoogleGeocoding(advancedSearchKeyword, function (result) {
     $('#input-advanced-search-data').val(JSON.stringify(result))
      console.log(JSON.stringify(result))
    })


    if (advancedSearchKeyword === "") {
      alert("Search input is empty")
      return false
    }
  })
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

function drawMap(places, latlngs) {
  map.setView(defaultCoordHCM, zoomLevel)
  L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '© <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  if (isRouting === true) {
    // create a red polyline from an array of LatLng points
    let polyline = L.polyline(latlngs, {color: 'red'}).addTo(map);
    // zoom the map to the polyline
    map.fitBounds(polyline.getBounds());
  }

  // code here show current
  places.map(place => {
    let routingLink = `/routing?from=${CURRENT_LOCATION}&to=${encodeURI(place.name)}`
    marker = L.marker([place.lat, place.lon])
    map.addLayer(marker)
    marker.bindPopup("<b>"+place.name+"</b><br><a href="+ routingLink +">Show directory</a>").openPopup();

  })
}

function searchByGoogleGeocoding(keyword, callback) {
  const searchPlace = []
  const urlEncodeKeyword = encodeURI(keyword)
  if (keyword !== CURRENT_LOCATION) {
    const key = formatMapAPIKey(GOOGLE_MAP_API_KEY)
    const geocodingAPI = hostingGeoCodingAPI + `?key=${key}` + `&address=${urlEncodeKeyword}`
    $.get(geocodingAPI, function (data, status) {
      if (status !== SUCCESS_STATUS) {
        console.log("Error 02 call searchByGoogleGeocoding: " + status)
        callback(searchPlace)
      }
      if (data.status !== OK_STATUS) {
        console.log("Error 01 call searchByGoogleGeocoding: " + status)
        console.log(data)
        alert(`No result with keyword ${keyword}. Please search again`)
        callback(searchPlace)
      }

      let searchResult = data.results
      searchResult.forEach(result => {
        let point = {
          "address": result.formatted_address,
          "lat": result.geometry.location.lat,
          "lon": result.geometry.location.lng,
          "name": keyword + " - " +result.address_components[0].long_name
        }
        searchPlace.push(point)
      })
      callback(searchPlace)

    })
  } else {
    determineCurrentLocation(postion => {
      searchPlace.push(postion)
      callback(searchPlace)
    })
  }
}

// bởi vì up len git là public Repository nên key cần chỉnh sửa xíu để tránh bị người khác dùng tool trộm xài
function formatMapAPIKey(api) {
  return api.split(".")[0]
}

function routingNodes(from, to) {
  scrollToMap()
  searchByGoogleGeocoding(from, function (fromResult) {
    searchByGoogleGeocoding(to, function (toResult) {
      if (fromResult.length !== 1) {
        alert("Cannot detect From address, Please choose another")
        return false
      }
      if (toResult.length !== 1) {
        alert("Cannot detect To address, Please choose another")
        return false
      }
      let fromObj = fromResult[0]
      let toObj = toResult[0]
      const routingEndPoint = "/api/v1.0/routing"
      const routingParam = `?route=${fromObj.lat},${fromObj.lon};${toObj.lat},${toObj.lon}`
      let routingAPI = host + routingEndPoint + routingParam;
      console.log(routingAPI)
      $.get(routingAPI, function (routingResult, status) {
        let coordinates = routingResult.geometry.coordinates
        if (status === SUCCESS_STATUS) {
          isRouting = true
          places.push({
            "address": fromObj.address,
            "lat": fromObj.lat,
            "lon": fromObj.lon,
            "name": fromObj.name
          })
          places.push({
            "address": toObj.address,
            "lat": toObj.lat,
            "lon": toObj.lon,
            "name": toObj.name
          })
          $('#alert-shortest-path').html(`Khoảng cách: ${routingResult.distance}`)
          $('#alert-from-to').html(`From: <b>${decodeURI(from)}</b> - To: <b>${decodeURI(to)}</b>`)
          drawMap(places, coordinates)
        }
      })
    })
  })
}

function handleFindPointOnMap(point) {
  //$('body').scroll({scrollTop: $("#map").offset().top})
  let pointArr = point.split('-')
  let lat = pointArr[0]
  let lon = pointArr[1]
  let name = pointArr[2]
  let address = point[3]
  places.push({name, lat, lon, address})
  drawMap(places, null)
}

function handleCurrentLocation() {
  determineCurrentLocation(location => {
    places.push(location)
    drawMap(places, null)
  })
}

function determineCurrentLocation(callback) {
  if (!currentLocation) {
    navigator.geolocation.getCurrentPosition(position => {
      let lat = position.coords.latitude
      let lon = position.coords.longitude
      let name = "My current location"
      let address = ""
      currentLocation = {lat, lon, name, address}
      callback(currentLocation)
    })
  } else {
    callback(currentLocation)
  }
}

function scrollToMap() {
  $('html, body').animate({
    scrollTop: $("#map").offset().top
  }, 1000);
}