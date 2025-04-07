import mapboxgl from "mapbox-gl";


export function initializeMap(container) {
    mapboxgl.accessToken = "pk.eyJ1IjoiZ3VvYXI3c2J2ZCIsImEiOiJjbTE5ZTRwNG8xNjB5MmpzYm5idG5scTEwIn0.OTqwjAzUggn3H0vXnRJP1Q";
    return new mapboxgl.Map({
        container: container,
        style: "mapbox://styles/guoar7sbvd/clwxoqjpj018i01pp39ag056t",
        center: [116.397428, 39.90923],
        zoom: 3,
        useWebGL2: true
    });
}