export class PlanStatusMapper {
    static #statusMap = {

        "preparing": "准备中",
        "taxing": "滑行中",
        "climbing": "爬升高度中",
        "cruising": "高空巡航中",
        "descending": "下降高度中",
        "approaching": "进近中",
        "landing": "即将着陆",
        "landed": "已经着陆",
        "parked": "在停机位中",
    }

    static getPosition(status){
        return this.#statusMap(status)
    }

    static getAllPositions() {
        return {...this.#statusMap};
    }

}