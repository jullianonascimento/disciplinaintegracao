package com.hmkcode;

/*import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;*/
import com.hmkcode.vo.Content;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Sending POST to GCM" );
        
        String apiKey = "AIzaSyDCLhEymszT6nCTjmbgFNrSyWkkDqTGmZY";
        Content content = createContent();
        System.out.println("conteudo criado");
        POST2GCM.post(apiKey, content);
    }
    
    public static Content createContent(){
		
		Content c = new Content();
		//regID hmkcode
		//c.addRegId("APA91bFEw44rwgsjKzEzJlaOv3GkwZHdTkq7z_qkRU-iTNepNsnIU0yKjluMfNEDA8H-ejYz6IdwOFrY7DYMYndVqw20bSzVqM-JKLaEL7AeKBkPMuXavqzwLJC0IkWlX9Ggx9ekf3UnR0U42wdeEw4e9PLWCOFyEUJeVySSvizDh15sCODMq1g");
		
		//regID android begin
		c.addRegId("APA91bGbCuzqLt3Csz6ngG49UQ3DEq1m44R8BZQQO6HbxcSOXHX0Kv-KarfoK-pSY4PTaxSmH5x8CyYl6AveTNwIi-rWiP6wL8R29tTs42CAHN_QbW6wb8lF7PDai-uuvLrvNqRYij52Au-kjp7NaIlazGlq4lNn2uhiFHg2UmKb9EEEkIekQ2Q");
		c.createData("Test Title", "Test Message: oi tudo bem com você?");
		
		return c;
	}
}
