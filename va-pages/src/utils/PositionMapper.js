export class PositionMapper {
    // 私有静态映射表
    static #positionMap = {
        0: '新飞行员',
        1: 'F1',
        2: 'F2',
        3: 'F3',
        4: 'F4',
        5: '助理教员',
        6: '正式教员',
        7: '副总经理',
        8: '总经理'
    };

    /**
     * 根据数字获取对应的职位
     * @param {number} code - 数字代码(0-8)
     * @returns {string} 对应的职位
     * @throws {Error} 如果输入的数字不在0-8的范围内
     */
    static getPosition(code) {
        if (code < 0 || code > 8 || !Number.isInteger(code)) {
            throw new Error('输入的数字必须在0-8的整数范围内');
        }
        return this.#positionMap[code];
    }

    /**
     * 获取所有职位映射
     * @returns {Object} 职位映射表
     */
    static getAllPositions() {
        return {...this.#positionMap};
    }

    /**
     * 检查数字是否有对应的职位
     * @param {number} code - 要检查的数字
     * @returns {boolean} 是否有对应的职位
     */
    static hasPosition(code) {
        return code >= 0 && code <= 8 && Number.isInteger(code);
    }
}