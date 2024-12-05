const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    allowedHosts: 'all', // 모든 호스트 허용 (선택 사항)

    headers: {
      'Access-Control-Allow-Origin': '*', // CORS 설정 (필요한 경우)
    },
  },
});
