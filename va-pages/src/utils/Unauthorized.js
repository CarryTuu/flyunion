export default function showUnauthorizedAlert() {
    // 创建提示框元素
    const alertBox = document.createElement('div');
    alertBox.style.position = 'fixed';
    alertBox.style.top = '50%';
    alertBox.style.left = '50%';
    alertBox.style.transform = 'translate(-50%, -50%)';
    alertBox.style.backgroundColor = '#fff';
    alertBox.style.padding = '20px';
    alertBox.style.borderRadius = '8px';
    alertBox.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.15)';
    alertBox.style.zIndex = '9999';
    alertBox.style.textAlign = 'center';
    alertBox.style.minWidth = '300px';

    // 创建标题
    const title = document.createElement('h3');
    title.textContent = '未登录';
    title.style.marginBottom = '15px';
    title.style.color = '#f56c6c';

    // 创建消息内容
    const message = document.createElement('p');
    message.textContent = '您当前未登录或您的权限不足，请重试';
    message.style.marginBottom = '20px';
    message.style.color = '#606266';

    // 创建倒计时提示
    const countdown = document.createElement('p');
    countdown.textContent = '3秒后自动返回首页...';
    countdown.style.fontSize = '14px';
    countdown.style.color = '#909399';

    // 添加到提示框
    alertBox.appendChild(title);
    alertBox.appendChild(message);
    alertBox.appendChild(countdown);

    // 添加到页面
    document.body.appendChild(alertBox);

    // 添加半透明背景遮罩
    const overlay = document.createElement('div');
    overlay.style.position = 'fixed';
    overlay.style.top = '0';
    overlay.style.left = '0';
    overlay.style.width = '100%';
    overlay.style.height = '100%';
    overlay.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
    overlay.style.zIndex = '9998';
    document.body.appendChild(overlay);

    // 倒计时更新
    let seconds = 3;
    const countdownInterval = setInterval(() => {
        seconds--;
        if (seconds > 0) {
            countdown.textContent = `${seconds}秒后自动返回首页...`;
        } else {
            clearInterval(countdownInterval);
        }
    }, 1000);

    // 3秒后移除提示框和遮罩
    setTimeout(() => {
        document.body.removeChild(alertBox);
        document.body.removeChild(overlay);
    }, 3000);
}