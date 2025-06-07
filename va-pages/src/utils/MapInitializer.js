import mapboxgl from "mapbox-gl";


export default function initializeMap(container) {
    mapboxgl.accessToken = "pk.eyJ1IjoiZ3VvYXI3c2J2ZCIsImEiOiJjbTE5ZTRwNG8xNjB5MmpzYm5idG5scTEwIn0.OTqwjAzUggn3H0vXnRJP1Q";
    return new mapboxgl.Map({
        container: container,
        style: "mapbox://styles/mapbox/dark-v10",
        center: [108.9236, 37.5408],
        zoom: 3.5,
        useWebGL2: true
    });
}