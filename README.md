# Java Experiments

Build the app and run tests.

```bash
mvn clean package
```

Run the app itself.

```bash
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

## HTTP interception

Install [mitmproxy](https://mitmproxy.org/).

```bash
sudo apt update
sudo apt install mitmproxy
```

Start the proxy.

```bash
mitmproxy
```

We can test it by making a curl request through the mitmproxy proxy.

```bash
curl --proxy http://127.0.0.1:8080 "https://example.org"
```

To intercepts Javas HTTP requests, we need to install the mitmproxy ca cert in javas keystore.

```bash
sudo keytool -importcert -file ~/.mitmproxy/mitmproxy-ca-cert.pem -alias mitmproxy -keystore "$JAVA_HOME/lib/security/cacerts" -storepass changeit
```

Finally we invoke maven and make it use the proxy when building and running tests:

```bash
mvn clean package -Dhttp.proxyHost=localhost -Dhttp.proxyPort=8080 -Dhttps.proxyHost=localhost -Dhttps.proxyPort=8080
```

Intercepting mTLS requires setting up mitmproxy with an upstream client certificate: <https://docs.mitmproxy.org/stable/concepts/certificates/#mtls-between-mitmproxy-and-upstream-server>
