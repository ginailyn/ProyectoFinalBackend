package BackEndC3.ClinicaOdontologica.security;

import BackEndC3.ClinicaOdontologica.entity.*;
import BackEndC3.ClinicaOdontologica.repository.OdontologoRepository;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import BackEndC3.ClinicaOdontologica.repository.TurnoRepository;
import BackEndC3.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado=  passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("Ana","Gonzalez","admin@admin.com",passCifrado, UsuarioRole.ROLE_ADMIN);
        Usuario usuario2= new Usuario("Gina","Arias","gina_a@gmail.com",passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);



    }
}
