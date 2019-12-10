module.exports = {
  css: {
    loaderOptions: {
      scss: {
        prependData: `@import "@/assets/scss/_variables.scss";
        @import "@/assets/scss/_mixins.scss";`
      }
    }
  }
}