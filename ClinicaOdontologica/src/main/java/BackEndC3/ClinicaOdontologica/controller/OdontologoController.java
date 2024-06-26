package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.entity.Odontologo;
import BackEndC3.ClinicaOdontologica.exception.ResourceNotFoundException;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> odontologoList() {
    //    List<Odontologo> listaOdontogos = odontologoService.buscarOdontologos();
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorID(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologos(@RequestBody Odontologo odontologo) {
        Odontologo nuevoOdontologo = odontologoService.crearOdontologos(odontologo);
        return ResponseEntity.ok(nuevoOdontologo);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorID(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            Odontologo odontologoActualizado = odontologoBuscado.get();
            odontologoActualizado.setNombre(odontologo.getNombre());
            odontologoActualizado.setApellido(odontologo.getApellido());
            odontologoActualizado.setMatricula(odontologo.getMatricula());

            odontologoService.actualizarOdontologo(odontologoActualizado);
            return ResponseEntity.ok("El Odontólogo se ha actualizado con éxito");
        } else {
            return ResponseEntity.badRequest().body("Odontólogo no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> odontologoAEliminar(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoConsultado = odontologoService.buscarPorID(id);
        if (odontologoConsultado.isPresent()) {
            odontologoService.odontologoAEliminar(id);
            return ResponseEntity.ok("Odontólogo eliminado exitosamente");
        } else {
           // return ResponseEntity.notFound().build();
            throw new ResourceNotFoundException("Turno no encontrado");
        }
    }
}
