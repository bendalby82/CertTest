package certs;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class Certs {
	public static void main (String[] args) {
		System.out.println("START");
		TrustManagerFactory trustManagerFactory = null;
		try {
			trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Certificate> x509Certificates = new ArrayList<>();
		try {
			trustManagerFactory.init((KeyStore) null);
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Arrays.asList(trustManagerFactory.getTrustManagers()).stream().forEach(t -> {
			x509Certificates.addAll(Arrays.asList(((X509TrustManager) t).getAcceptedIssuers()));
		});
		
		for (Certificate cert: x509Certificates) {
			System.out.println(cert.toString());
		}
		
		System.out.println("END AND SLEEP");
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
