// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package aqcommon.actions;

import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import aqcommon.CertificateHelper;

/**
 * Encrypts a string using the RSA algorithm and a public key from a cer file (must be an X509 certificate)
 * 
 * CerFileName : The name of the file stored in resources folder, e.g. 'MyCerFile.cer'. Can also be a path from the resources folder, e.g. 'certs\\MyCerFile.cer'
 * ToEncrypt: The string to encrypt.
 * 
 * Returns a string containing a base64 encoded representation of the encrypted string.
 */
public class EncryptStringWithPublicKey extends CustomJavaAction<java.lang.String>
{
	private final java.lang.String CerFileName;
	private final java.lang.String ToEncrypt;

	public EncryptStringWithPublicKey(
		IContext context,
		java.lang.String _cerFileName,
		java.lang.String _toEncrypt
	)
	{
		super(context);
		this.CerFileName = _cerFileName;
		this.ToEncrypt = _toEncrypt;
	}

	@java.lang.Override
	public java.lang.String executeAction() throws Exception
	{
		// BEGIN USER CODE
		if(this.CerFileName == null || this.CerFileName.equalsIgnoreCase(""))
			throw new IllegalArgumentException("CerFileName cannot be empty.");
						
		PublicKey pk = CertificateHelper.GetPublicKeyFromCerFile(this.CerFileName);
		
		Cipher cipher =Cipher.getInstance("RSA");
		
		cipher.init(Cipher.ENCRYPT_MODE, pk);

		byte[] encrypted = cipher.doFinal(this.ToEncrypt.getBytes());
				
		return Base64.getEncoder().encodeToString(encrypted);
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "EncryptStringWithPublicKey";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
