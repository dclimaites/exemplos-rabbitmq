package ninja.hesketh.produtor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorFanout {
    public static void main(String[] args) {
        RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnectionDiego());
        Queue queueCor = new Queue("torcedor.corinthians");
        Queue queuePalmeiras = new Queue("torcedor.palmeiras");
        Queue queueSantos = new Queue("torcedor.santos");
        Queue queueSP = new Queue("torcedor.spfc");

        admin.declareQueue(queueCor);
        admin.declareQueue(queuePalmeiras);
        admin.declareQueue(queueSantos);
        admin.declareQueue(queueSP);

        FanoutExchange exchange = new FanoutExchange("exchangeFanoutDiego");
        admin.declareExchange(exchange);

        admin.declareBinding(BindingBuilder.bind(queueCor).to(exchange));
        admin.declareBinding(BindingBuilder.bind(queuePalmeiras).to(exchange));
        admin.declareBinding(BindingBuilder.bind(queueSantos).to(exchange));
        admin.declareBinding(BindingBuilder.bind(queueSP).to(exchange));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnectionDiego());

        template.convertAndSend("exchangeDiego", "corinthians", "Coringão Diego Fanout");
        template.convertAndSend("exchangeDiego", "palmeiras", "parmera Diego  Fanout");
        template.convertAndSend("exchangeDiego", "santos", "santos Diego  Fanout");
        template.convertAndSend("exchangeDiego", "spfc", "São Paulo Diego  Fanout");
    }
}
