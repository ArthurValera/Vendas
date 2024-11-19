package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Estado;
import com.pjt.vendas.repositorios.EstadoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import java.util.Optional;

@Controller
public class EstadoControle {
    @Autowired
    private EstadoRep estadoRep;

    @GetMapping("/cadastraEstado")
    public ModelAndView cadastrar(Estado estado){
        ModelAndView mv = new ModelAndView("/adm/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listaEstado")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/estados/lista");
        mv.addObject("listaEstado", estadoRep.findAll());
        return mv;
    }

    @GetMapping("/editaEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRep.findById(id);
        return cadastrar(estado.get());
    }

    @GetMapping("/removeEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRep.findById(id);
        estadoRep.delete(estado.get());
        return listar();
    }

    @PostMapping("/salvaEstado")
    public ModelAndView salvar(Estado estado, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(estado);
        }
        estadoRep.saveAndFlush(estado);
        return cadastrar(new Estado());
    }
}
