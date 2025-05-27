/**
 * 带数字标记的旋转长方形绘图工具
 *
 * 功能：在圆形内绘制旋转长方形并在两端添加数字
 *
 * 使用示例：
 * import CircleRectDrawer from './circleRectDrawer.js'
 *
 * // 在Vue组件中
 * mounted() {
 *   const canvas = this.$refs.myCanvas
 *   CircleRectDrawer.draw(
 *     canvas,
 *     canvas.width/2,  // 圆心X
 *     canvas.height/2, // 圆心Y
 *     300,             // 圆直径
 *     175,             // 长方形长度
 *     15,              // 长方形宽度
 *     45,              // 旋转角度(0-360)
 *     [1, 2]           // 两端数字 [起始端, 结束端]
 *   )
 * }
 */

export default {
    /**
     * 绘制带数字标记的旋转长方形
     * @param {HTMLCanvasElement} canvas - Canvas元素
     * @param {number} centerX - 圆心X坐标
     * @param {number} centerY - 圆心Y坐标
     * @param {number} diameter - 圆直径(px)
     * @param {number} rectLength - 长方形长度(px)
     * @param {number} rectWidth - 长方形宽度(px)
     * @param {number} rotateAngle - 旋转角度(0-360度)
     * @param {Array<number>} endNumbers - 两端数字 [起始端数字, 结束端数字]
     * @param {object} [styles] - 样式配置
     * @param {string} [styles.circleFill='transparent'] - 圆填充色
     * @param {string} [styles.circleStroke='#000'] - 圆描边色
     * @param {number} [styles.circleStrokeWidth=2] - 圆描边宽度
     * @param {string} [styles.rectFill='#3498db'] - 长方形填充色
     * @param {string} [styles.textColor='#000'] - 数字颜色
     * @param {number} [styles.textSize=16] - 数字大小(px)
     * @param {string} [styles.textFont='Arial'] - 数字字体
     */
    draw(
        canvas,
        centerX,
        centerY,
        diameter,
        rectLength,
        rectWidth,
        rotateAngle,
        endNumbers = [0, 1],
        styles = {}
    ) {
        // 参数验证
        if (!(canvas instanceof HTMLCanvasElement)) {
            console.error('第一个参数必须是Canvas元素');
            return;
        }
        if (!Array.isArray(endNumbers) || endNumbers.length !== 2) {
            console.error('endNumbers必须是包含两个数字的数组');
            return;
        }

        // 合并默认样式
        const {
            circleFill = 'transparent',
            circleStroke = '#000',
            circleStrokeWidth = 2,
            rectFill = '#3498db',
            textColor = '#000',
            textSize = 16,
            textFont = 'Arial'
        } = styles;

        const ctx = canvas.getContext('2d');
        const radius = diameter / 2;

        // 清除画布
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        // 绘制圆形
        ctx.beginPath();
        ctx.arc(centerX, centerY, radius, 0, Math.PI * 2);
        if (circleFill !== 'transparent') {
            ctx.fillStyle = circleFill;
            ctx.fill();
        }
        ctx.strokeStyle = circleStroke;
        ctx.lineWidth = circleStrokeWidth;
        ctx.stroke();

        // 保存当前绘图状态
        ctx.save();

        // 移动坐标系到圆心
        ctx.translate(centerX, centerY);

        // 旋转坐标系（角度转弧度）
        const angleInRad = (rotateAngle * Math.PI) / 180;
        ctx.rotate(angleInRad);

        // 绘制长方形（以旋转后的坐标系原点为中心）
        ctx.fillStyle = rectFill;
        ctx.fillRect(
            -rectWidth / 2,    // x位置（居中）
            -rectLength / 2,   // y位置（居中）
            rectWidth,         // 宽度
            rectLength         // 高度
        );

        // 设置文字样式
        ctx.fillStyle = textColor;
        ctx.font = `${textSize}px ${textFont}`;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';

        // 计算数字位置（长方形两端延伸10px的位置）
        const textOffset = rectLength / 2 + 15;

        // 绘制起始端数字（自动补零）
        ctx.fillText(
            endNumbers[0], // 使用格式化后的数字
            0,
            -textOffset
        );

        // 绘制结束端数字
        ctx.fillText(
            endNumbers[1], // 使用格式化后的数字
            0,
            textOffset
        );

        // 恢复绘图状态
        ctx.restore();
    },

    /**
     * 初始化Canvas（适配高清屏幕）
     * @param {HTMLCanvasElement} canvas
     * @param {number} width
     * @param {number} height
     */
    initCanvas(canvas, width, height) {
        const dpr = window.devicePixelRatio || 1;
        canvas.width = width * dpr;
        canvas.height = height * dpr;
        canvas.style.width = `${width}px`;
        canvas.style.height = `${height}px`;
        const ctx = canvas.getContext('2d');
        ctx.scale(dpr, dpr);
    }
};