function fn() {
  var env = karate.env;
  if (!env) {
    env = 'dev';
  }

  var dbUrl = java.lang.System.getenv('DB_URL');
  var dbUser = java.lang.System.getenv('DB_USER');
  var dbPass = java.lang.System.getenv('DB_PASS');
  var appEmail = java.lang.System.getenv('APP_EMAIL');
  var appPass = java.lang.System.getenv('APP_PASS');

  var config = {
    env: env,
    baseUrl: '',
    dbConfig: null,
    auth: {
      email: appEmail,
      password: appPass
    }
  }

  if (env == 'dev') {
    config.baseUrl = 'http://localhost:3000';
    config.dbConfig = {
        url: dbUrl,
        username: dbUser,
        password: dbPass
    }
  } else if (env == 'qa') {
    config.baseUrl = 'http://localhost:3000';
    config.dbConfig = {
        url: dbUrl,
        username: dbUser,
        password: dbPass
    }
  }

  var DbUtils = Java.type('br.com.workhelper.utils.DbUtils');
  config.db = new DbUtils(config.dbConfig);

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);

  return config;
}