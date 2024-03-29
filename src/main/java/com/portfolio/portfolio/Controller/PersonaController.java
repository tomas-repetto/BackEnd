package com.portfolio.portfolio.Controller;

import com.portfolio.portfolio.Entity.Persona;
import com.portfolio.portfolio.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://argentinaprogramafronten-9e640.web.app/")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fue creada.";
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona fue borrada";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona (@PathVariable Long id,
            @RequestParam("nombre") String muevoNombre,
            @RequestParam("apellido") String muevoApellido,
            @RequestParam("img") String muevoImg){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(muevoNombre);
        persona.setApellido(muevoApellido);
        persona.setImg(muevoImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    @GetMapping("/personas/traer/pefil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)2);
    }
}
