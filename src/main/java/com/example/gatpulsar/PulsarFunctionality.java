package com.example.gatpulsar;

import java.util.HashMap;
import java.util.Map;
import com.datastax.oss.pulsar.jms.PulsarConnectionFactory;

import javax.jms.*;

public class PulsarFunctionality {
    private String cert;
    private String key;
    private String tlsCertsTrust;
    private String env;
    private String webServiceUrl;
    private String broker;
    private  String authPlugin = "org.apache.client.impl.auth.Authentication";

    private JMSContext pulsarConnection;

    public PulsarFunctionality(String cert, String key, String tlsCertsTrust, String env) throws JMSException {
        this.cert =cert;
        this.key = key;
        this.tlsCertsTrust = tlsCertsTrust;
        this.env = env;
        pulsarConnection=connection(this.cert,this.key,this.tlsCertsTrust, this.env);
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

    public void sendMessage(String topic, String message){
        Queue queue = this.pulsarConnection.createQueue(topic);
        this.pulsarConnection.createConsumer(queue).setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println("Mensagem recebida"+message);
                }catch (Exception e){  System.out.println("Erro ao enviar receber a mensagem"+message);}
            }
        });
        this.pulsarConnection.createProducer().send(queue,message);
    }

    /* Not implemented in gitHub
        But this method define de values for Broker and WebServiceUrl, depends of ev variable
     */
    private void setBrokerAndWebService() {
        switch (this.env){
            case "DEV":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
            case "TST":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
            case "TSI":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
            case "CER":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
            case "QLY":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
            case "PRD":
                    this.broker= "";
                    this.webServiceUrl ="";
                break;
                default:
                    this.broker= "";
                    this.webServiceUrl ="";
        }
    }
}
