package ninja.hesketh.produtor;

import ninja.hesketh.configuracao.Configuracao;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class ProdutorDirect {
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

        DirectExchange exchange = new DirectExchange("exchangeDiego");
        admin.declareExchange(exchange);

        admin.declareBinding(BindingBuilder.bind(queueCor).to(exchange).with("corinthians"));
        admin.declareBinding(BindingBuilder.bind(queuePalmeiras).to(exchange).with("palmeiras"));
        admin.declareBinding(BindingBuilder.bind(queueSantos).to(exchange).with("santos"));
        admin.declareBinding(BindingBuilder.bind(queueSP).to(exchange).with("spfc"));

        RabbitTemplate template = new RabbitTemplate(Configuracao.getConnectionDiego());

        template.convertAndSend("exchangeDiego", "corinthians", "Coringão Diego");
        template.convertAndSend("exchangeDiego", "palmeiras", "parmera Diego");
        template.convertAndSend("exchangeDiego", "santos", "santos Diego");
        template.convertAndSend("exchangeDiego", "spfc", "São Paulo Diego");
    }
}
