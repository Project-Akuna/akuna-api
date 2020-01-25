const path = require('path');

module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'),
        '@store': path.resolve(__dirname, 'src/store'),
        '@utils': path.resolve(__dirname, 'src/utils'),
        '@components': path.resolve(__dirname, 'src/components'),
        '@layouts': path.resolve(__dirname, 'src/components/layouts'),
        '@staticPages': path.resolve(__dirname, 'src/components/static_pages'),
      }
    }
  },
  css: {
    loaderOptions: {
      scss: {
        prependData: `@import "@/assets/scss/_variables.scss";
        @import "@/assets/scss/_mixins.scss";`
      }
    }
  },
  chainWebpack: config => {
    config.module.rules.delete('eslint');
  }
}
