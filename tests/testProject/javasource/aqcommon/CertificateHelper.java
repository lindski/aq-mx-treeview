package aqcommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import com.mendix.core.Core;

public class CertificateHelper {

	public static PrivateKey GetPrivateKeyFromPfxFile(String fileName, String password, String alias) throws Exception {
		File pfxFile = new File(Core.getConfiguration().getResourcesPath().toString() + 
				File.separatorChar + fileName);
		InputStream pfxInputStream = new FileInputStream(pfxFile);
		
		KeyStore pkcsKeyStore = KeyStore.getInstance("pkcs12");
		pkcsKeyStore.load(pfxInputStream, password.toCharArray());
		pfxInputStream.close();

		Key key = pkcsKeyStore.getKey(alias, password.toCharArray());
		
		if(key == null)
			throw new IllegalArgumentException("No key found for specified PfxAlias.");
		
		PrivateKey pk = (PrivateKey)key;
		
		return pk;
	}
	
	public static PublicKey GetPublicKeyFromCerFile(String fileName) throws Exception {
		File certFile = new File(Core.getConfiguration().getResourcesPath().toString() + File.separatorChar + fileName);
		
		InputStream certInputStream = new FileInputStream(certFile);
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		Certificate certificate = certFactory.generateCertificate(certInputStream);
		PublicKey pk = certificate.getPublicKey();
		
		return pk;
	}
}
