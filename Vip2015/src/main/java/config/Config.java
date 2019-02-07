package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.AlineaRepository;
import repository.BancoRepository;
import repository.ChequeRepository;
import repository.ClienteRepository;

public class Config {
    /* comunicaÃ§Ã£o com o banco */
    public static final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext (DBConfig.class);
    public static final BancoRepository bancoRepository = ctx.getBean(BancoRepository.class);
    public static final AlineaRepository alineaRepository = ctx.getBean(AlineaRepository.class);
    public static final ClienteRepository clienteRepository = ctx.getBean(ClienteRepository.class);
    public static final ChequeRepository chequeRepository = ctx.getBean(ChequeRepository.class);
    public static final char INCLUIR = 'I';
    public static final char ALTERAR = 'A';
    
//final estabelece uma constante
}
