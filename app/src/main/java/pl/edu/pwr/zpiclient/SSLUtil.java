package pl.edu.pwr.zpiclient;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import javax.net.ssl.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.X509Certificate;

public final class SSLUtil{

    private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                    return null;
                }
                public void checkClientTrusted( X509Certificate[] certs, String authType ){}
                public void checkServerTrusted( X509Certificate[] certs, String authType ){}
            }
    };

    public  static void turnOffSslChecking() throws NoSuchAlgorithmException, KeyManagementException {
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init( null, UNQUESTIONING_TRUST_MANAGER, null );
        //HttpsURLConnection.getDefaultSSLSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new AllowAllHostnameVerifier());
    }

    public static void turnOnSslChecking() throws KeyManagementException, NoSuchAlgorithmException {
        // Return it to the initial state (discovered by reflection, now hardcoded)
        SSLContext.getInstance("SSL").init( null, null, null );
    }

    private SSLUtil(){
        throw new UnsupportedOperationException( "Do not instantiate libraries.");
    }
}