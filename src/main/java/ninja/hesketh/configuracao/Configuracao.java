package ninja.hesketh.configuracao;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

public class Configuracao {
    private static CachingConnectionFactory connectionFactory;
    private static CachingConnectionFactory connectionFactoryDiego;

    public static CachingConnectionFactory getConnection(){

        if(connectionFactory == null){
            connectionFactory = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
            connectionFactory.setUsername("vdchtboi");
            connectionFactory.setPassword("JLkDUowPEwxXYO5aROVRMxXHSMvSz_gI");
            connectionFactory.setVirtualHost("vdchtboi");

            //Recommended settings
            connectionFactory.setRequestedHeartBeat(30);
            connectionFactory.setConnectionTimeout(30000);
        }

        return connectionFactory;
    }

    public static CachingConnectionFactory getConnectionDiego(){

        if(connectionFactoryDiego == null){
            connectionFactoryDiego = new CachingConnectionFactory("spider.rmq.cloudamqp.com");
            connectionFactoryDiego.setUsername("tjylytmo");
            connectionFactoryDiego.setPassword("7OfClKsZZhiZYMu1ybqiwPDiiBcEJ8re");
            connectionFactoryDiego.setVirtualHost("tjylytmo");

            //Recommended settings
            connectionFactoryDiego.setRequestedHeartBeat(30);
            connectionFactoryDiego.setConnectionTimeout(30000);
        }

        return connectionFactoryDiego;
    }
}
