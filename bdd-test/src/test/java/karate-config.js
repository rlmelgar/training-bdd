function fn() {
  karate.log('[KARATE START]');
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev'; // a custom 'intelligent' default
  }
  var config = {
      baseUrl : 'http://localhost:8085/imperial'
  };
  karate.log('[KARATE DEBUG] Config Settled:', config);

  karate.log('[KARATE END]');
  return config;
}