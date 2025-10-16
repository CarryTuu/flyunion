export class PermissionMapper {
    // 私有静态映射表
    static #permissionMap = {
        1: '飞行员',
        2: '签派员',
        3: '航司经理',
        4: '系统管理员'
    };

    /**
     * 根据数字获取对应的职位
     * @param {number} code - 数字代码(0-8)
     * @returns {string} 对应的职位
     * @throws {Error} 如果输入的数字不在0-8的范围内
     */
    static getPermission(code) {
        if (code < 0 || code > 8 || !Number.isInteger(code)) {
            throw new Error('输入的数字必须在0-8的整数范围内');
        }
        return this.#permissionMap[code];
    }

    /**
     * 获取所有职位映射
     * @returns {Object} 职位映射表
     */
    static getAllPermission() {
        return {...this.#permissionMap};
    }

    /**
     * 检查数字是否有对应的职位
     * @param {number} code - 要检查的数字
     * @returns {boolean} 是否有对应的职位
     */
    static hasPermission(code) {
        return code >= 0 && code <= 8 && Number.isInteger(code);
    }
}