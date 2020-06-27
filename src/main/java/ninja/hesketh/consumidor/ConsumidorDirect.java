package ninja.hesketh.consumidor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ConsumidorDirect {
    public static void main(String[] args) {
        //Set up queue, exchanges and bindings
        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnectionDiego());

        for (int i = 0; i < 10; i++) {
            byte[] body = template.receive("torcedor.corinthians").getBody();
            System.out.println(new String(body));
        }


    }
}
