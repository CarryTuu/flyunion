import * as AMapLoader from "@amap/amap-jsapi-loader";
import AMap from "@amap/amap-jsapi-loader"

let mapInstance = null

export default function initAMap(container){
    if(mapInstance){
        return Promise.resolve(mapInstance);
    }
    window._AMapSecurityConfig = "f66753f0117bac61f8b6e56e3a31c230";

    const defaultOptions = {
        viewMode: "3D",
        zoom: 5,
        center: [108.9236, 34.5408],
        mapStyle: "amap://styles/grey"
    };

    AMapLoader.load({
        key: "c2e6e41dec5661f4f14d75cc127dc62a",
        plugins: ["AMap.Scale", "AMap.ToolBar"]
    })
        .then((AMap) => {
            mapInstance = new AMap.Map(container, defaultOptions)
            AMap.plugin('AMap.ToolBar',function(){
                var toolbar = new AMap.ToolBar(); //缩放工具条实例化
                var Scale = new AMap.Scale();
                mapInstance.addControl(toolbar); //添加控件
                mapInstance.addControl(Scale); //添加控件
            });
            return mapInstance;
        })
}
/**
 * 获取地图实例
 * @returns {object|null}
 */
export function getMapInstance() {
    return mapInstance;
}

/**
 * 销毁地图实例
 */
export function destroyMap() {
    if (mapInstance) {
        mapInstance.destroy();
        mapInstance = null;
    }
}

export function newPolyLine(map, path = []){
    var polyline = new AMap.Polyline({
        path: path,
        strokeWeight: 2, //线条宽度
        strokeColor: "green", //线条颜色
        lineJoin: "round", //折线拐点连接处样式
    });
    map.add(polyline);
}