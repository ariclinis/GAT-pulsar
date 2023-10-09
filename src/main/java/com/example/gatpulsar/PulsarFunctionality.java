package com.example.gatpulsar;

import java.util.HashMap;
import java.util.Map;
import com.datastax.oss.pulsar.jms.PulsarConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

public class PulsarFunctionality {
    private String cert;
    private String key;
    private String tlsCertsTrust;
    private String env;
    private String webServiceUrl;
    private String broker;
    private  String authPlugin;

    private  JMSContext pulsarConnection;

    public PulsarFunctionality(String cert, String key, String tlsCertsTrust, String env) throws JMSException {
        this.cert =cert;
        this.key = key;
        this.tlsCertsTrust = tlsCertsTrust;
        this.env = env;
        pulsarConnection=connection(cert,key,tlsCertsTrust, env);
    }

    private JMSContext connection(String cert, String key, String tlsCertsTrust, String env) throws JMSException {
        setBrokerAndWebService();
        Map<String, Object> properties = new HashMap<>();
        properties.put("webServiceUrl", this.webServiceUrl);
        properties.put("brokerServiceUrl", this.broker);
        properties.put("authPlugin", this.authPlugin);
        properties.put("authParams", "tlsCertFile:"+cert+","+"tlsKeyFile:"+key);
        properties.put("tlsTrustCertsFilePath", tlsCertsTrust);

        try (PulsarConnectionFactory factory = new PulsarConnectionFactory(properties)){
            return factory.createContext();
        }catch(JMSException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    /* Not implemented in gitHub
        But this method define de values for Broker and WebServiceUrl, depends of ev variable
     */
    private void setBrokerAndWebService() {

    }
}
