
import role_util from './utils/role_util';

export default {
  install(app) {
    app.config.globalProperties.$roleUtils = role_util;
  }
};
